package com.android.baotrithietbi.domain.usecase.device

import com.android.baotrithietbi.domain.TestData
import com.android.baotrithietbi.domain.repository.device.DeviceRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SaveDeviceUseCaseTest {
    private val repo = mockk<DeviceRepository>()
    private val useCase = SaveDeviceUseCase(repo)

    @Test
    fun `invoke_emptyCode_returnsFailure`() = runTest {
        val invalidDevice = TestData.device.copy(code = "")
        val result = useCase(invalidDevice)

        assert(result.isFailure)
        assertEquals("Mã thiết bị không được để trống", result.exceptionOrNull()?.message)
    }

    @Test
    fun `invoke_addNewDeviceWithDuplicateCode_returnsFailure`() = runTest {
        // Given: Thiết bị mới (id = 0) nhưng mã đã tồn tại trong DB
        val newDevice = TestData.device.copy(id = 0L, code = "DUP001")
        coEvery { repo.getDeviceByCode("DUP001") } returns TestData.device

        // When
        val result = useCase(newDevice)

        // Then
        assert(result.isFailure)
        assertTrue(result.exceptionOrNull()?.message?.contains("đã tồn tại") == true)
        coVerify(exactly = 0) { repo.addDevice(any()) }
    }

    @Test
    fun `invoke_updateExistingDevice_callsUpdateRepo`() = runTest {
        // Given: Thiết bị đã có id
        coEvery { repo.updateDevice(any()) } returns Unit

        // When
        val result = useCase(TestData.device)

        // Then
        assert(result.isSuccess)
        coVerify(exactly = 1) { repo.updateDevice(TestData.device) }
    }
}
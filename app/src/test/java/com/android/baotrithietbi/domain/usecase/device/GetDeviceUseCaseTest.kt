package com.android.baotrithietbi.domain.usecase.device

import com.android.baotrithietbi.domain.TestData
import com.android.baotrithietbi.domain.repository.device.DeviceRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetDeviceUseCaseTest {
    private val repo = mockk<DeviceRepository>()
    private val useCase = GetDeviceUseCase(repo)

    @Test
    fun `invokeByCode_deviceNotFound_returnsFailure`() = runTest {
        coEvery { repo.getDeviceByCode(any()) } returns null

        val result = useCase.invokeByCode("UNKNOWN")

        assert(result.isFailure)
        assertTrue(result.exceptionOrNull()?.message?.contains("Không tìm thấy") == true)
    }

    @Test
    fun `invokeByCode_validCode_returnsSuccessWithTrimmedInput`() = runTest {
        // Given
        val inputCode = "  dev001  "
        val expectedCode = "DEV001"
        coEvery { repo.getDeviceByCode(expectedCode) } returns TestData.device

        // When
        val result = useCase.invokeByCode(inputCode)

        // Then
        assert(result.isSuccess)
        assertEquals(TestData.device, result.getOrNull())
    }
}
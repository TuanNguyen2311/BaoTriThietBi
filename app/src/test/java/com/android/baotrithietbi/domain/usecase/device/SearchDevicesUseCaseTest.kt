package com.android.baotrithietbi.domain.usecase.device

import com.android.baotrithietbi.domain.TestData
import com.android.baotrithietbi.domain.repository.device.DeviceRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SearchDevicesUseCaseTest {
    private val repo = mockk<DeviceRepository>()
    private val useCase = SearchDevicesUseCase(repo)

    @Test
    fun `invoke_emptyQuery_callsGetAllDevices`() = runTest {
        // Given
        val allDevices = listOf(TestData.device)
        every { repo.getAllDevices() } returns flowOf(allDevices)

        // When
        val flow = useCase("")

        // Then
        flow.collect { result ->
            assertEquals(allDevices, result)
        }
        verify(exactly = 1) { repo.getAllDevices() }
        verify(exactly = 0) { repo.searchDevices(any()) }
    }

    @Test
    fun `invoke_withQuery_callsSearchDevices`() = runTest {
        // Given
        every { repo.searchDevices("máy nén") } returns flowOf(listOf(TestData.device))

        // When
        val flow = useCase("máy nén")

        // Then
        flow.collect { result ->
            assertEquals(1, result.size)
        }
        verify(exactly = 1) { repo.searchDevices("máy nén") }
    }
}
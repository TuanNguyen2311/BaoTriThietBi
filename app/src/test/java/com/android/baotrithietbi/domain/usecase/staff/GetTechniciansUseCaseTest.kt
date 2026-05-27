package com.android.baotrithietbi.domain.usecase.staff

import com.android.baotrithietbi.domain.TestData
import com.android.baotrithietbi.domain.repository.auth.ITechnicianRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetTechniciansUseCaseTest {
    private val repo = mockk<ITechnicianRepository>()
    private val useCase = GetTechniciansUseCase(repo)

    @Test
    fun `invoke_returnsFlowFromRepository`() = runTest {
        // Given
        val mockList = listOf(TestData.technician)
        every { repo.getAllTechnicians() } returns flowOf(mockList)

        // When
        val resultFlow = useCase()

        // Then
        resultFlow.collect { technicians ->
            assertEquals(1, technicians.size)
            assertEquals("ktv.tung", technicians[0].username)
        }
        verify(exactly = 1) { repo.getAllTechnicians() }
    }
}
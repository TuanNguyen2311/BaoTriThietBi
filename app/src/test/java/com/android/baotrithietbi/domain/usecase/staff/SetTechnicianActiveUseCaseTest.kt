package com.android.baotrithietbi.domain.usecase.staff

import com.android.baotrithietbi.domain.repository.auth.ITechnicianRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SetTechnicianActiveUseCaseTest {
    private val repo = mockk<ITechnicianRepository>()
    private val useCase = SetTechnicianActiveUseCase(repo)

    @Test
    fun `invoke_callsRepoWithCorrectParameters`() = runTest {
        // Given
        coEvery { repo.setTechnicianActive(any(), any()) } returns Unit

        // When
        val result = useCase(userId = 10L, active = false)

        // Then
        assert(result.isSuccess)
        coVerify(exactly = 1) { repo.setTechnicianActive(10L, false) }
    }

    @Test
    fun `invoke_repoThrowsException_returnsFailure`() = runTest {
        // Given
        coEvery { repo.setTechnicianActive(any(), any()) } throws Exception("Database Error")

        // When
        val result = useCase(userId = 10L, active = true)

        // Then
        assert(result.isFailure)
        assertEquals("Database Error", result.exceptionOrNull()?.message)
    }
}
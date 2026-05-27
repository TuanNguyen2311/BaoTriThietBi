package com.android.baotrithietbi.domain.usecase.staff

import com.android.baotrithietbi.domain.repository.auth.ITechnicianRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CreateTechnicianUseCaseTest {
    private val repo = mockk<ITechnicianRepository>()
    private val useCase = CreateTechnicianUseCase(repo)

    @Test
    fun `invoke_emptyUsername_returnsFailure`() = runTest {
        val result = useCase(username = "", password = "password123", fullName = "Name")

        assert(result.isFailure)
        assertEquals("Tên đăng nhập không được để trống", result.exceptionOrNull()?.message)
        coVerify(exactly = 0) { repo.createTechnician(any(), any(), any()) }
    }

    @Test
    fun `invoke_passwordTooShort_returnsFailure`() = runTest {
        val result = useCase(username = "tung.ktv", password = "123", fullName = "Name")

        assert(result.isFailure)
        assertEquals("Mật khẩu phải có ít nhất 6 ký tự", result.exceptionOrNull()?.message)
    }

    @Test
    fun `invoke_validData_callsRepoWithTrimmedUsernameAndReturnsSuccess`() = runTest {
        // Given
        val rawUsername = "  TUNG.KTV  "
        val expectedUsername = "tung.ktv"
        coEvery { repo.createTechnician(expectedUsername, any(), any()) } returns 10L

        // When
        val result = useCase(username = rawUsername, password = "password123", fullName = "Nguyễn Văn Tùng")

        // Then
        assert(result.isSuccess)
        assertEquals(10L, result.getOrNull())
        coVerify(exactly = 1) { repo.createTechnician(expectedUsername, "password123", "Nguyễn Văn Tùng") }
    }
}
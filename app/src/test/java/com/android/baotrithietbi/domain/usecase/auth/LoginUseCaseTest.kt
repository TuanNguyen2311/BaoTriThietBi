package com.android.baotrithietbi.domain.usecase.auth

import com.android.baotrithietbi.domain.TestData
import com.android.baotrithietbi.domain.repository.auth.IAuthRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class LoginUseCaseTest {
    private val authRepo = mockk<IAuthRepository>()
    private val loginUseCase = LoginUseCase(authRepo)

    @Test
    fun `invoke_validCredentials_returnsSuccess`() = runTest {
        // Given
        val username = " TUAN.PHONG " // Test tính năng trim() và lowercase()
        val password = "password123"
        coEvery { authRepo.login("tuan.phong", password) } returns TestData.authUser

        // When
        val result = loginUseCase(username, password)

        // Then
        assert(result.isSuccess)
        assertEquals(TestData.authUser, result.getOrNull())
    }

    @Test
    fun `invoke_invalidCredentials_returnsFailure`() = runTest {
        // Given
        coEvery { authRepo.login(any(), any()) } returns null

        // When
        val result = loginUseCase("admin", "wrong_pass")

        // Then
        assert(result.isFailure)
        assertEquals("Tên đăng nhập hoặc mật khẩu không đúng", result.exceptionOrNull()?.message)
    }
}
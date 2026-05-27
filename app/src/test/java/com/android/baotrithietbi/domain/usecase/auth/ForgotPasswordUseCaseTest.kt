package com.android.baotrithietbi.domain.usecase.auth

import com.android.baotrithietbi.domain.TestData
import com.android.baotrithietbi.domain.repository.auth.ISecurityRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test


class ForgotPasswordUseCaseTest {
    private val securityRepo = mockk<ISecurityRepository>()
    private val forgotPasswordUseCase = ForgotPasswordUseCase(securityRepo)

    @Test
    fun `verifyPinWithUsername_validUsernameAndPin_returnsProfile`() = runTest {
        // Given
        val username = "tuan.phong"
        val pin = "123456"
        coEvery { securityRepo.getUserByUsername(username) } returns TestData.securityProfile
        coEvery { securityRepo.verifyPin(TestData.securityProfile.userId, pin) } returns true

        // When
        val result = forgotPasswordUseCase.verifyPinWithUsername(username, pin)

        // Then
        assert(result.isSuccess)
        assertEquals(TestData.securityProfile, result.getOrNull())
    }

    @Test
    fun `verifyPinWithUsername_wrongPin_returnsFailure`() = runTest {
        // Given
        coEvery { securityRepo.getUserByUsername(any()) } returns TestData.securityProfile
        coEvery { securityRepo.verifyPin(any(), any()) } returns false

        // When
        val result = forgotPasswordUseCase.verifyPinWithUsername("admin", "000000")

        // Then
        assert(result.isFailure)
        assertEquals("PIN không đúng", result.exceptionOrNull()?.message)
    }

    @Test
    fun `resetPassword_mismatchConfirmPassword_returnsError`() = runTest {
        // When
        val result = forgotPasswordUseCase.resetPassword(1L, "NewPass123", "MismatchPass")

        // Then
        assert(result.isFailure)
        assertEquals("Mật khẩu xác nhận không khớp", result.exceptionOrNull()?.message)
        coVerify(exactly = 0) { securityRepo.changePassword(any(), any()) }
    }
}
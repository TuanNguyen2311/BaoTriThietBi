package com.android.baotrithietbi.domain.usecase.auth

import com.android.baotrithietbi.domain.repository.auth.ISecurityRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SetupPinUseCaseTest {
    private val securityRepo = mockk<ISecurityRepository>()
    private val setupPinUseCase = SetupPinUseCase(securityRepo)

    @Test
    fun `invoke_pinInvalidFormat_returnsError`() = runTest {
        // Scenario: PIN không đủ độ dài hoặc chứa chữ cái
        val invalidPins = listOf("123", "12345a", "      ")

        invalidPins.forEach { pin ->
            val result = setupPinUseCase(1L, pin, pin)
            assert(result.isFailure)
            assertEquals("PIN phải là 6 chữ số", result.exceptionOrNull()?.message)
        }
        coVerify(exactly = 0) { securityRepo.setupPin(any(), any()) }
    }

    @Test
    fun `invoke_pinMismatch_returnsError`() = runTest {
        // Scenario: PIN xác nhận không khớp
        val result = setupPinUseCase(1L, "123456", "654321")

        assert(result.isFailure)
        assertEquals("PIN xác nhận không khớp", result.exceptionOrNull()?.message)
    }

    @Test
    fun `invoke_validPin_callsRepositoryAndReturnsSuccess`() = runTest {
        // Given
        coEvery { securityRepo.setupPin(any(), any()) } returns Unit

        // When
        val result = setupPinUseCase(1L, "123456", "123456")

        // Then
        assert(result.isSuccess)
        coVerify(exactly = 1) { securityRepo.setupPin(1L, "123456") }
    }
}
package com.android.baotrithietbi.domain.usecase.auth

import com.android.baotrithietbi.domain.model.auth.AuthConstants.MIN_PASSWORD_LENGTH
import com.android.baotrithietbi.domain.repository.auth.ISecurityRepository
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(private val securityRepo: ISecurityRepository) {
    suspend operator fun invoke(userId: Long, newPassword: String, confirmPassword: String): Result<Unit> {
        if (newPassword.length < MIN_PASSWORD_LENGTH)
            return Result.failure(Exception("Mật khẩu phải có ít nhất 6 ký tự"))
        if (newPassword != confirmPassword)
            return Result.failure(Exception("Mật khẩu xác nhận không khớp"))
        if (!newPassword.any { it.isUpperCase() } || !newPassword.any { it.isLowerCase() })
            return Result.failure(Exception("Mật khẩu phải có chữ hoa và chữ thường"))

        securityRepo.changePassword(userId, newPassword)
        return Result.success(Unit)
    }
}
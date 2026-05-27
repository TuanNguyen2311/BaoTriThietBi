package com.android.baotrithietbi.domain.usecase.auth

import com.android.baotrithietbi.domain.model.auth.AuthConstants.MIN_PASSWORD_LENGTH
import com.android.baotrithietbi.domain.model.auth.AuthConstants.PIN_LENGTH
import com.android.baotrithietbi.domain.model.auth.UserSecurityProfile
import com.android.baotrithietbi.domain.repository.auth.ISecurityRepository
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(private val securityRepo: ISecurityRepository) {

    suspend fun verifyPinWithUsername(username: String, pin: String): Result<UserSecurityProfile> {
        if (username.isBlank())
            return Result.failure(Exception("Tên đăng nhập không được để trống"))
        if (pin.length != PIN_LENGTH)
            return Result.failure(Exception("PIN phải là 6 chữ số"))

        // Trả về UserSecurityProfile (chứa id, hasPin, fullName)
        val userProfile = securityRepo.getUserByUsername(username.trim().lowercase())
            ?: return Result.failure(Exception("Không tìm thấy tài khoản \"$username\""))

        // Verify PIN thông qua SecurityRepo
        val valid = securityRepo.verifyPin(userProfile.userId, pin)
        return if (valid) Result.success(userProfile)
        else Result.failure(Exception("PIN không đúng"))
    }

    suspend fun resetPassword(userId: Long, newPassword: String, confirmPassword: String): Result<Unit> {
        if (newPassword.length < MIN_PASSWORD_LENGTH)
            return Result.failure(Exception("Mật khẩu phải có ít nhất 6 ký tự"))
        if (newPassword != confirmPassword)
            return Result.failure(Exception("Mật khẩu xác nhận không khớp"))

        securityRepo.changePassword(userId, newPassword)
        return Result.success(Unit)
    }
}
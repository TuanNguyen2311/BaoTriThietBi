package com.android.baotrithietbi.domain.usecase.auth

import com.android.baotrithietbi.domain.model.auth.AuthUser
import com.android.baotrithietbi.domain.repository.auth.IAuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepo: IAuthRepository) {
    suspend operator fun invoke(username: String, password: String): Result<AuthUser> {
        val user = authRepo.login(username.trim().lowercase(), password)
            ?: return Result.failure(Exception("Tên đăng nhập hoặc mật khẩu không đúng"))
        return Result.success(user)
    }
}
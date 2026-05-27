package com.android.baotrithietbi.domain.usecase.staff

import com.android.baotrithietbi.domain.repository.auth.ITechnicianRepository
import javax.inject.Inject

class CreateTechnicianUseCase @Inject constructor(
    private val repository: ITechnicianRepository
) {
    suspend operator fun invoke(username: String, password: String, fullName: String): Result<Long> {
        if (username.isBlank()) return Result.failure(Exception("Tên đăng nhập không được để trống"))
        if (password.length < 6) return Result.failure(Exception("Mật khẩu phải có ít nhất 6 ký tự"))
        if (fullName.isBlank()) return Result.failure(Exception("Họ tên không được để trống"))

        return try {
            val id = repository.createTechnician(username.trim().lowercase(), password, fullName)
            Result.success(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
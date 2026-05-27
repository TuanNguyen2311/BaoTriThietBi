package com.android.baotrithietbi.domain.usecase.staff

import com.android.baotrithietbi.domain.repository.auth.ITechnicianRepository
import javax.inject.Inject

class DeleteTechnicianUseCase @Inject constructor(
    private val repository: ITechnicianRepository
) {
    suspend operator fun invoke(userId: Long): Result<Unit> {
        return try {
            repository.deleteTechnician(userId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
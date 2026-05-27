package com.android.baotrithietbi.domain.usecase.staff

import com.android.baotrithietbi.domain.repository.auth.ITechnicianRepository
import javax.inject.Inject

class SetTechnicianActiveUseCase @Inject constructor(
    private val repository: ITechnicianRepository
) {
    suspend operator fun invoke(userId: Long, active: Boolean): Result<Unit> {
        return try {
            repository.setTechnicianActive(userId, active)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
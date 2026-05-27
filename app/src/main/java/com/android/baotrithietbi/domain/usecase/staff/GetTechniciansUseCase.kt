package com.android.baotrithietbi.domain.usecase.staff

import com.android.baotrithietbi.domain.model.auth.Technician
import com.android.baotrithietbi.domain.repository.auth.ITechnicianRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTechniciansUseCase @Inject constructor(
    private val repository: ITechnicianRepository
) {
    operator fun invoke(): Flow<List<Technician>> {
        return repository.getAllTechnicians()
    }
}
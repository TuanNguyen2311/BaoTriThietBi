package com.android.baotrithietbi.domain.repository.auth

import com.android.baotrithietbi.domain.model.auth.Technician
import kotlinx.coroutines.flow.Flow

interface ITechnicianRepository {
    fun getAllTechnicians(): Flow<List<Technician>>

    suspend fun getTechnicianById(id: Long): Technician?

    suspend fun createTechnician(username: String, password: String, fullName: String): Long
    suspend fun setTechnicianActive(userId: Long, active: Boolean)
    suspend fun resetTechnicianPassword(userId: Long)
    suspend fun deleteTechnician(userId: Long)
}
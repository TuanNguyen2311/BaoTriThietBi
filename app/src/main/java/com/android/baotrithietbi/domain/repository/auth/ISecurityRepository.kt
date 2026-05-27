package com.android.baotrithietbi.domain.repository.auth

import com.android.baotrithietbi.domain.model.auth.UserSecurityProfile

interface ISecurityRepository {
    suspend fun getUserByUsername(username: String): UserSecurityProfile?

    suspend fun setupPin(userId: Long, pin: String)
    suspend fun verifyPin(userId: Long, pin: String): Boolean

    suspend fun changePassword(userId: Long, newPassword: String)
    suspend fun resetPasswordWithPin(userId: Long, pin: String, newPassword: String): Boolean
}
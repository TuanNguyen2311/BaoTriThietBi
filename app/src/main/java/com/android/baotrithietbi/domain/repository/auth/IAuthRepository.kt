package com.android.baotrithietbi.domain.repository.auth

import com.android.baotrithietbi.domain.model.auth.AuthUser

interface IAuthRepository {
    suspend fun login(username: String, password: String): AuthUser?
    suspend fun hasManagerAccount(): Boolean
    suspend fun isFirstLogin(userId: Long): Boolean
}
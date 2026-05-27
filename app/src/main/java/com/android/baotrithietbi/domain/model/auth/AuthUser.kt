package com.android.baotrithietbi.domain.model.auth

enum class Role { MANAGER, TECHNICIAN }
data class AuthUser(
    val id: Long,
    val username: String,
    val role: Role,
    val isFirstLogin: Boolean
)

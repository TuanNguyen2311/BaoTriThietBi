package com.android.baotrithietbi.domain.model.auth

data class UserSecurityProfile(
    val userId: Long,
    val hasPin: Boolean,
    val fullName: String
)

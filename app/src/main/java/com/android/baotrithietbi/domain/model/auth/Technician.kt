package com.android.baotrithietbi.domain.model.auth

data class Technician(
    val id: Long,
    val username: String,
    val fullName: String,
    val isActive: Boolean,
    val createdAt: Long
)

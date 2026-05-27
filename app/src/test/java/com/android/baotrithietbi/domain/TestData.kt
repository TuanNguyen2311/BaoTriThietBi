package com.android.baotrithietbi.domain

import com.android.baotrithietbi.domain.model.auth.AuthUser
import com.android.baotrithietbi.domain.model.auth.Role
import com.android.baotrithietbi.domain.model.auth.Technician
import com.android.baotrithietbi.domain.model.auth.UserSecurityProfile


object TestData {
    val authUser = AuthUser(
        id = 1L,
        username = "tuan.phong",
        role = Role.MANAGER,
        isFirstLogin = false
    )

    val securityProfile = UserSecurityProfile(
        userId = 1L,
        hasPin = true,
        fullName = "Nguyen Anh Tuan"
    )

    val technician = Technician(
        id = 10L,
        username = "ktv.tung",
        fullName = "Nguyễn Thanh Tùng",
        isActive = true,
        createdAt = System.currentTimeMillis()
    )
}
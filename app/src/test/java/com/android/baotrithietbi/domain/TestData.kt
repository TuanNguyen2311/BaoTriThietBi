package com.android.baotrithietbi.domain

import com.android.baotrithietbi.domain.model.auth.AuthUser
import com.android.baotrithietbi.domain.model.auth.Role
import com.android.baotrithietbi.domain.model.auth.Technician
import com.android.baotrithietbi.domain.model.auth.UserSecurityProfile
import com.android.baotrithietbi.domain.model.device.Device


object TestData {
    //----------------AUTH FEATURE-------------------
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
    //----------------DEVICE MANAGER FEATURE-------------------
    val device = Device(
        id = 1L,
        code = "DEV001",
        name = "Máy nén khí Piston",
        category = "Máy công nghiệp",
        location = "Khu vực A",
        buyDate = System.currentTimeMillis(),
        warrantyDate = System.currentTimeMillis() + 31536000000L, // +1 năm
        photoPath = null,
        qrPath = null,
        notes = "Thiết bị mới"
    )
}
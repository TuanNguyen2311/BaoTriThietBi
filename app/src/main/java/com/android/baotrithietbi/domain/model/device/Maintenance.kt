package com.android.baotrithietbi.domain.model.device

enum class MaintenanceType {
    PERIODIC, EMERGENCY, INSPECTION;

    fun displayName() = when(this) {
        PERIODIC    -> "Bảo trì định kỳ"
        EMERGENCY   -> "Sửa chữa đột xuất"
        INSPECTION  -> "Kiểm tra"
    }
}

enum class MaintenanceStatus {
    RESOLVED, WAITING_PARTS, UNRESOLVED;

    fun displayName() = when(this) {
        RESOLVED       -> "Đã xử lý xong"
        WAITING_PARTS  -> "Chờ phụ tùng"
        UNRESOLVED     -> "Chưa xử lý"
    }
}
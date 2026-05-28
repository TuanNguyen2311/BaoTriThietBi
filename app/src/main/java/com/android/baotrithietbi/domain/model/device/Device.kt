package com.android.baotrithietbi.domain.model.device

data class Device(
    val id: Long,
    val code: String,
    val name: String,
    val category: String,
    val location: String,
    val buyDate: Long?,
    val warrantyDate: Long?,
    val photoPath: String?,
    val qrPath: String?,
    val notes: String?,
    val latestStatus: MaintenanceStatus? = null,
    val logCount: Int = 0
) {
    val isWarrantyExpired: Boolean
        get() = warrantyDate != null && warrantyDate < System.currentTimeMillis()

    val isWarrantyExpiringSoon: Boolean
        get() {
            if (warrantyDate == null) return false
            val thirtyDays = 30L * 24 * 60 * 60 * 1000
            return warrantyDate > System.currentTimeMillis() &&
                    warrantyDate < System.currentTimeMillis() + thirtyDays
        }
}

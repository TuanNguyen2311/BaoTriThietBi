package com.android.baotrithietbi.domain.usecase.device

import com.android.baotrithietbi.domain.TestData
import junit.framework.TestCase.assertTrue
import org.junit.Test

class DeviceModelTest {
    @Test
    fun `isWarrantyExpired_pastDate_returnsTrue`() {
        val expiredDevice = TestData.device.copy(
            warrantyDate = System.currentTimeMillis() - 10000 // Đã hết hạn 10s trước
        )
        assertTrue(expiredDevice.isWarrantyExpired)
    }

    @Test
    fun `isWarrantyExpiringSoon_within30Days_returnsTrue`() {
        val fiveDaysInMs = 5L * 24 * 60 * 60 * 1000
        val soonDevice = TestData.device.copy(
            warrantyDate = System.currentTimeMillis() + fiveDaysInMs
        )
        assertTrue(soonDevice.isWarrantyExpiringSoon)
    }
}
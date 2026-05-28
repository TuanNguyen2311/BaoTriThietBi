package com.android.baotrithietbi.domain.usecase.device

import com.android.baotrithietbi.domain.repository.device.DeviceRepository
import javax.inject.Inject

class DeleteDeviceUseCase @Inject constructor(private val repo: DeviceRepository) {
    suspend operator fun invoke(deviceId: Long): Result<Unit> {
        return try {
            repo.deleteDevice(deviceId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
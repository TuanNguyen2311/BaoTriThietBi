package com.android.baotrithietbi.domain.usecase.device

import com.android.baotrithietbi.domain.model.device.Device
import com.android.baotrithietbi.domain.repository.device.DeviceRepository
import javax.inject.Inject

class SaveDeviceUseCase @Inject constructor(private val repo: DeviceRepository) {
    suspend operator fun invoke(device: Device): Result<Long> {
        if (device.code.isBlank()) return Result.failure(Exception("Mã thiết bị không được để trống"))
        if (device.name.isBlank()) return Result.failure(Exception("Tên thiết bị không được để trống"))
        if (device.category.isBlank()) return Result.failure(Exception("Danh mục không được để trống"))
        if (device.location.isBlank()) return Result.failure(Exception("Vị trí không được để trống"))

        return try {
            if (device.id == 0L) {
                // Check duplicate code
                val existing = repo.getDeviceByCode(device.code)
                if (existing != null)
                    return Result.failure(Exception("Mã thiết bị '${device.code}' đã tồn tại"))
                val id = repo.addDevice(device)
                Result.success(id)
            } else {
                repo.updateDevice(device)
                Result.success(device.id)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateQrPath(deviceId: Long, path: String) {
        if (path.isBlank()) return
        repo.updateQrPath(deviceId, path)
    }
}
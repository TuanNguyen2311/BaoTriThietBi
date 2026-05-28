package com.android.baotrithietbi.domain.usecase.device

import com.android.baotrithietbi.domain.model.device.Device
import com.android.baotrithietbi.domain.repository.device.DeviceRepository
import javax.inject.Inject

class GetDeviceUseCase @Inject constructor(private val repo: DeviceRepository) {
    suspend fun invokeByCode(code: String): Result<Device> {
        val device = repo.getDeviceByCode(code.trim().uppercase())
            ?: return Result.failure(Exception("Không tìm thấy thiết bị với mã: $code"))
        return Result.success(device)
    }
    suspend fun invokeById(id: Long): Result<Device> {
        val device = repo.getDeviceById(id)
            ?: return Result.failure(Exception("Không tìm thấy thiết bị với id: $id"))
        return Result.success(device)
    }
}
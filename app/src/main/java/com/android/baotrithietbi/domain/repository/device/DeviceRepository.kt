package com.android.baotrithietbi.domain.repository.device

import com.android.baotrithietbi.domain.model.device.Device
import kotlinx.coroutines.flow.Flow

interface DeviceRepository {
    fun getAllDevices(): Flow<List<Device>>
    fun searchDevices(query: String): Flow<List<Device>>
    fun filterByArea(area: String): Flow<List<Device>>//next version
    suspend fun getDeviceById(id: Long): Device?
    suspend fun getDeviceByCode(code: String): Device?
    suspend fun addDevice(device: Device): Long
    suspend fun updateDevice(device: Device)
    suspend fun deleteDevice(deviceId: Long)
    suspend fun updateQrPath(deviceId: Long, path: String)
    suspend fun getStats(): Triple<Int, Int, Int>//extend to more stats
}
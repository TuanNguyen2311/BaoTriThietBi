package com.android.baotrithietbi.domain.usecase.device

import com.android.baotrithietbi.domain.model.device.Device
import com.android.baotrithietbi.domain.repository.device.DeviceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllDevicesUseCase @Inject constructor(private val repo: DeviceRepository) {
    operator fun invoke(): Flow<List<Device>> = repo.getAllDevices()
}
package com.android.baotrithietbi.domain.usecase.device

import com.android.baotrithietbi.domain.model.device.Device
import com.android.baotrithietbi.domain.repository.device.DeviceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchDevicesUseCase @Inject constructor(private val repo: DeviceRepository) {
    operator fun invoke(query: String): Flow<List<Device>> =
        if (query.isBlank()) repo.getAllDevices() else repo.searchDevices(query)
}
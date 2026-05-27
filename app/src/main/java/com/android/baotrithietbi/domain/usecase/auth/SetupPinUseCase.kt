package com.android.baotrithietbi.domain.usecase.auth

import com.android.baotrithietbi.domain.model.auth.AuthConstants.PIN_LENGTH
import com.android.baotrithietbi.domain.repository.auth.ISecurityRepository
import javax.inject.Inject

class SetupPinUseCase @Inject constructor(private val securityRepo: ISecurityRepository) {
    suspend operator fun invoke(userId: Long, pin: String, confirmPin: String): Result<Unit> {
        if (pin.length != PIN_LENGTH || !pin.all { it.isDigit() })
            return Result.failure(Exception("PIN phải là 6 chữ số"))
        if (pin != confirmPin)
            return Result.failure(Exception("PIN xác nhận không khớp"))

        securityRepo.setupPin(userId, pin)
        return Result.success(Unit)
    }
}
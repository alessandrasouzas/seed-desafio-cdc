package com.ale.cdc.livraria.infrastructure.exception

import java.time.LocalDateTime

data class MessageError(
    val code: String,
    val message: String,
)
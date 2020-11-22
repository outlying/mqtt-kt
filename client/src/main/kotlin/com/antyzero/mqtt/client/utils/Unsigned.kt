package com.antyzero.mqtt.client.utils


@ExperimentalUnsignedTypes
infix fun UByte.shl(b: Int) = toUInt().shl(b).toUByte()
@ExperimentalUnsignedTypes
infix fun UByte.shr(b: Int) = toUInt().shr(b).toUByte()
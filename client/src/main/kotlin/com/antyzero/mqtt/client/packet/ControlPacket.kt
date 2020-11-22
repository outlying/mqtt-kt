package com.antyzero.mqtt.client.packet

import com.antyzero.mqtt.client.utils.shl
import com.antyzero.mqtt.client.utils.shr

@ExperimentalUnsignedTypes
class ControlPacket(byteArray: UByteArray) {

    init {
        require(byteArray.isNotEmpty()) { "Empty byte array is not accepted" }

        val fixedHeaderBits = byteArray[0]

        val typeByte = fixedHeaderBits shr 4
    }

    class Buider() {

    }
}
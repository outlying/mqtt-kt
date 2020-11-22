package com.antyzero.mqtt.client.packet

import org.junit.jupiter.api.Test

class ControlPacketTest {

    @Test
    fun checkType() {
        val byte = (15.shl(4)).toUByte()

        val type = (240 shr 4).toUByte()

        print(type)
    }
}
package com.antyzero.mqtt.client.utils

import org.junit.jupiter.api.Test

@ExperimentalUnsignedTypes
class UnsignedTest {

    @Test
    internal fun shl() {
        val ubyte = 15.toUByte()

        val result = ubyte shl 4

        assert(result == 240.toUByte())
    }

    @Test
    internal fun shr() {
        val ubyte = 240.toUByte()

        val result = ubyte shr 4

        assert(result == 15.toUByte())
    }
}
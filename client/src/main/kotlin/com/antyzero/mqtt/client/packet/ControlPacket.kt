package com.antyzero.mqtt.client.packet

@ExperimentalUnsignedTypes
class ControlPacket(byteArray: UByteArray) {

    init {
        require(byteArray.isNotEmpty()) { "Empty byte array is not accepted" }

        val fixedHeader = byteArray[0]

        123 shl 2
    }

    class Buider() {

    }
}
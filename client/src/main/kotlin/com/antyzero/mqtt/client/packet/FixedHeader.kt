package com.antyzero.mqtt.client.packet

open class FixedHeader(
        private val type: Type,
        private val flags: UByte
) {

    class PublishFixedHeader(type: Type, flags: UByte) : FixedHeader(type, flags)
}
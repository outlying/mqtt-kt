package com.antyzero.mqtt.client.packet

@ExperimentalUnsignedTypes
enum class Type(
        private val value: UByte
) {
    CONNECT(1u),
    CONNACK(2u),
    PUBLISH(3u),
    PUBACK(4u),
    PUBREC(5u),
    PUBREL(6u),
    PUBCOMP(7u),
    SUBSCRIBE(8u),
    SUBACK(9u),
    UNSUBSCRIBE(10u),
    UNSUBACK(11u),
    PINGREQ(12u),
    PINGRESP(13u),
    DISCONNECT(14u),
    AUTH(15u);

    fun find(value: UByte) = Type.values().firstOrNull { it.value == value }
            ?: throw IllegalStateException("Unsupported value $value")
}
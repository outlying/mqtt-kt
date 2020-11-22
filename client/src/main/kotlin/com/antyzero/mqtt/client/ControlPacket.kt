package com.antyzero.mqtt.client

import java.lang.IllegalStateException


/**
 * List of packets types according to
 *
 * https://docs.oasis-open.org/mqtt/mqtt/v5.0/os/mqtt-v5.0-os.html#_Toc3901022
 */
sealed class ControlPacket(
        val type: Byte,
        val flags: Flags,
        val variableHeader: VariableHeader?
) {

    init {
        assert(type in 0..15) { "Type has to be in [0..15] range" }
    }

    class Connect : ControlPacket(1, Flags.Default, null)

    class Connack : ControlPacket(2, Flags.Default, null)

    class Publish(
            qualityOfService: Byte,
            duplicateDelivery: Boolean,
            retain: Boolean
    ) : ControlPacket(3, Flags.Publish(qualityOfService, duplicateDelivery, retain), VariableHeader())

    class Puback : ControlPacket(4, Flags.Default, VariableHeader())

    class Pubrec : ControlPacket(5, Flags.Default, VariableHeader())

    class Pubrel : ControlPacket(6, Flags.Common, VariableHeader())

    class Pubcomp : ControlPacket(7, Flags.Default, VariableHeader())

    class Subscribe : ControlPacket(8, Flags.Common, VariableHeader())

    class Suback : ControlPacket(9, Flags.Default, VariableHeader())

    class Unsubscribe : ControlPacket(10, Flags.Common, VariableHeader())

    class Unsuback : ControlPacket(11, Flags.Default, VariableHeader())

    class Pingreq : ControlPacket(12, Flags.Default, null)

    class Pingresp : ControlPacket(13, Flags.Default, null)

    class Disconnect : ControlPacket(14, Flags.Default, null)

    class Auth : ControlPacket(15, Flags.Default, null)

    /**
     *
     */
    class VariableHeader

    /**
     * Represents bits in order from highest to lowest
     */
    sealed class Flags(private val flags: Array<Boolean>) {

        object Default : Flags(arrayOf(false, false, false, false))

        object Common : Flags(arrayOf(false, false, true, false))

        class Publish(
                qualityOfService: Byte,
                duplicateDelivery: Boolean,
                retain: Boolean
        ) : Flags(listOf(duplicateDelivery)
                .plus(qualityOfService.convertToBytes())
                .plus(retain)
                .toTypedArray()) {

            companion object {

                private fun Byte.convertToBytes() : Collection<Boolean> {
                    return when(this) {
                        3.toByte() -> arrayListOf(true, true)
                        2.toByte() -> arrayListOf(true, false)
                        1.toByte() -> arrayListOf(false, true)
                        0.toByte() -> arrayListOf(false, false)
                        else -> throw IllegalStateException("Accepted values from 0 to 3")
                    }
                }
            }
        }
    }
}
package com.antyzero.mqtt.client


/**
 * List of packets types according to
 *
 * https://docs.oasis-open.org/mqtt/mqtt/v3.1.1/os/mqtt-v3.1.1-os.html#_Table_2.1_-
 * https://docs.oasis-open.org/mqtt/mqtt/v5.0/os/mqtt-v5.0-os.html#_Toc3901022
 */
sealed class ControlPacket(
    val type: Int,
    val flags: Array<Boolean>,
    val variableHeader: VariableHeader?
) {

    init {
        assert(type in 0..15) { "Type has to be in [0..15] range" }
        assert(flags.size == 4) { "Four bits required" }
    }

    class Connect : ControlPacket(1, FLAGS_DEFAULT, null)

    class Connack : ControlPacket(2, FLAGS_DEFAULT, null)

    // TODO variable header is optional here, only if QoS > 0
    class Publish : ControlPacket(3, createPublishFlags(), VariableHeader())

    class Puback : ControlPacket(4, FLAGS_DEFAULT, VariableHeader())

    class Pubrec : ControlPacket(5, FLAGS_DEFAULT, VariableHeader())

    class Pubrel : ControlPacket(6, FLAGS_COMMON, VariableHeader())

    class Pubcomp : ControlPacket(7, FLAGS_DEFAULT, VariableHeader())

    class Subscribe : ControlPacket(8, FLAGS_COMMON, VariableHeader())

    class Suback : ControlPacket(9, FLAGS_DEFAULT, VariableHeader())

    class Unsubscribe : ControlPacket(10, FLAGS_COMMON, VariableHeader())

    class Unsuback : ControlPacket(11, FLAGS_DEFAULT, VariableHeader())

    class Pingreq : ControlPacket(12, FLAGS_DEFAULT, null)

    class Pingresp : ControlPacket(13, FLAGS_DEFAULT, null)

    class Disconnect : ControlPacket(14, FLAGS_DEFAULT, null)

    /**
     *
     */
    class VariableHeader

    companion object {

        val FLAGS_DEFAULT = arrayOf(false, false, false, false)
        val FLAGS_COMMON = arrayOf(false, false, true, false)

        fun createPublishFlags(): Array<Boolean> {
            return arrayOf()
        }
    }
}
package com.antyzero.mqtt.client.packet


/**
 * List of packets types according to
 *
 * https://docs.oasis-open.org/mqtt/mqtt/v5.0/os/mqtt-v5.0-os.html#_Toc3901022
 */
sealed class ControlPacketDeprecated(
        val type: Byte,
        val flags: Flags,
        val variableHeader: VariableHeader?
) {

    init {
        assert(type in 0..15) { "Type has to be in [0..15] range" }
    }

    class Connect : ControlPacketDeprecated(1, Flags.Default, null)

    class Connack : ControlPacketDeprecated(2, Flags.Default, null)

    class Publish(
            qualityOfService: Byte,
            duplicateDelivery: Boolean,
            retain: Boolean
    ) : ControlPacketDeprecated(3, Flags.Publish(qualityOfService, duplicateDelivery, retain), VariableHeader())

    class Puback : ControlPacketDeprecated(4, Flags.Default, VariableHeader())

    class Pubrec : ControlPacketDeprecated(5, Flags.Default, VariableHeader())

    class Pubrel : ControlPacketDeprecated(6, Flags.Common, VariableHeader())

    class Pubcomp : ControlPacketDeprecated(7, Flags.Default, VariableHeader())

    class Subscribe : ControlPacketDeprecated(8, Flags.Common, VariableHeader())

    class Suback : ControlPacketDeprecated(9, Flags.Default, VariableHeader())

    class Unsubscribe : ControlPacketDeprecated(10, Flags.Common, VariableHeader())

    class Unsuback : ControlPacketDeprecated(11, Flags.Default, VariableHeader())

    class Pingreq : ControlPacketDeprecated(12, Flags.Default, null)

    class Pingresp : ControlPacketDeprecated(13, Flags.Default, null)

    class Disconnect : ControlPacketDeprecated(14, Flags.Default, null)

    class Auth : ControlPacketDeprecated(15, Flags.Default, null)

    /**
     *
     */
    class VariableHeader

}
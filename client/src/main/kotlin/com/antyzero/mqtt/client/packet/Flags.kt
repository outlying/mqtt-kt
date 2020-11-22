package com.antyzero.mqtt.client.packet

import java.lang.IllegalStateException

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
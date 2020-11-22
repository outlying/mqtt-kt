package com.antyzero.mqtt.client

import com.antyzero.mqtt.client.packet.ControlPacketDeprecated
import okio.*
import java.net.Socket


class MqttKt(
    val host: String,
    val port: Int = 1883
) {

    init {
        require(port > 0) { "Port value have to be higher than 0" }
    }

    fun start()  {

        val connectionSocket = Socket(host, port)

        val source: BufferedSource = connectionSocket.source().buffer()
        val sink: BufferedSink = connectionSocket.sink().buffer()

        ControlPacketDeprecated.Pubrel()

        while (true) {
            println("Is connected ${connectionSocket.isConnected}")
            println(String(source.readByteString().toByteArray()))
        }
    }
}
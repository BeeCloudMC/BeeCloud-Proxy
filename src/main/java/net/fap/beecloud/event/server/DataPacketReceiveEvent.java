package net.fap.beecloud.event.server;

import net.fap.beecloud.network.mcpe.protocol.BeeCloudPacket;

public class DataPacketReceiveEvent extends ServerEvent {

    public DataPacketReceiveEvent(BeeCloudPacket packet) {
        this.packet = packet;
    }

}

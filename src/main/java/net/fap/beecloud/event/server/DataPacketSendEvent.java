package net.fap.beecloud.event.server;

import net.fap.beecloud.network.mcpe.protocol.BeeCloudPacket;

public class DataPacketSendEvent extends ServerEvent {

    public DataPacketSendEvent(BeeCloudPacket packet) {
        this.packet = packet;
    }

}

package net.fap.beecloud.event.synapse;

import net.fap.beecloud.Client;

public class ClientConnectEvent extends SynapseEvent {

    public ClientConnectEvent(Client client) {
        this.client = client;
    }

}

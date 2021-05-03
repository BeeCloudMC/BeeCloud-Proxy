package net.fap.beecloud.event.synapse;

import net.fap.beecloud.Client;
import net.fap.beecloud.event.Event;

public class SynapseEvent extends Event {

	public Client client;

	public Client getClient() {
		return this.client;
	}

}

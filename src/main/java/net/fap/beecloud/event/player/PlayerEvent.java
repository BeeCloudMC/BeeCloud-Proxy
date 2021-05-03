package net.fap.beecloud.event.player;

import net.fap.beecloud.event.Event;

public class PlayerEvent extends Event {

	public PlayerEvent() {
	}

	public String player;

	public String getPlayer() {
		return this.player;
	}

	@Override
	public void setCancelled() {
		super.setCancelled();
	}

	@Override
	public String getEventName() {
		return super.getEventName();
	}

}

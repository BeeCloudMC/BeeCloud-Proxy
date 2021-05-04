package net.fap.beecloud.event.player;

import net.fap.beecloud.SynapsePlayer;

public class PlayerCommandEvent extends PlayerEvent {

    private String commandStr;
    private String message;

    public PlayerCommandEvent(SynapsePlayer player, String commandStr, String commandMessage) {
        this.player = player.getName();
        this.commandStr = commandStr;
        this.message = commandMessage;
    }

    public String getMessage() {
        return message;
    }

    public String getCommandStr() {
        return commandStr;
    }

}

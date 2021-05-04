package net.fap.beecloud.network.mcpe.protocol;

import net.fap.beecloud.SynapsePlayer;

/**
 * 玩家背包数据包
 * 当玩家从一个服务器进入和退出时会发送这个数据包
 * 以实现背包同步
 *
 * @author catrainbow
 */

public class InventoryPacket extends BeeCloudPacket {

    private int[] item = new int[36];
    private int[] count = new int[36];
    private String[] name = new String[36];
    private String player;
    private String info;

    public InventoryPacket(String packet) {
        this.info = packet;
    }

    public String getPlayer() {
        return player;
    }

    @Override
    public void resend() {
        SynapsePlayer.getPlayer(player).getInventory().putItemArray(this);
        //TODO: update player's inventory in nukkit
        /**
         * @see net.fap.beecloud.inventory.PlayerInventory
         */
    }

    @Override
    public String to_String() {
        return info;
    }

    public int[] getItem() {
        return item;
    }

    public int[] getCount() {
        return count;
    }

    public String[] getName() {
        return name;
    }

    @Override
    public void putString(String[] pk2) {
        this.player = pk2[1];
        for (int i = 2; i < 37; i++)
            item[i] = Integer.valueOf(pk2[i]);
        for (int i = 37; i < 72; i++)
            count[i] = Integer.valueOf(pk2[i]);
        for (int i = 72; i < 107; i++)
            name[i] = String.valueOf(pk2[i]);
    }

}

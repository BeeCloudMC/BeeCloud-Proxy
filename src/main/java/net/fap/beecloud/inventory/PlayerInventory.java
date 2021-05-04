package net.fap.beecloud.inventory;

import net.fap.beecloud.SynapsePlayer;
import net.fap.beecloud.network.mcpe.protocol.InventoryPacket;

public class PlayerInventory {

    public SynapsePlayer player;

    public int[] itemID = new int[36];
    public int[] itemCount = new int[36];
    public String[] itemName = new String[36];
    public Item[] items = new Item[36];

    public PlayerInventory(SynapsePlayer player) {
        this.player = player;
    }

    public void putItemArray(InventoryPacket packet) {
        this.itemID = packet.getItem();
        this.itemCount = packet.getCount();
        this.itemName = packet.getName();
        for (int i = 0; i < 36; i++)
            items[i] = createItem(itemID[i], itemCount[i], itemName[i]);
    }

    public Item createItem(int id, int count, String name) {
        Item item = new Item(id, 0, count, name);
        return item;
    }

    public Item getItem(int index) {
        return items[index];
    }

    public void clearAll() {
        //TODO: Inventory Packet resend
        /**
         * @see InventoryPacket
         */
    }

}

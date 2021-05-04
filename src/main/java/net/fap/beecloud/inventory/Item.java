package net.fap.beecloud.inventory;

public class Item {

    private int id;
    private int count;
    private String name;

    public Item(int id, Integer meta, int count, String name) {
        this.id = id;
        this.count = count;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setCustomName(String name) {
        this.name = name;
    }

}

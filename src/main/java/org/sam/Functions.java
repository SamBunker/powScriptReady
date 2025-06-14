package org.sam;

import org.powbot.api.event.GameObjectActionEvent;
import org.powbot.api.rt4.*;

public class Functions {

    public Functions() {
        super();
    }

    public static boolean hasItem(String name) {
        return Inventory.stream().nameContains(name).isNotEmpty() ||
                Equipment.stream().nameContains(name).isNotEmpty();
    }

    public static Item getFirstInventoryItemByID(int id) {
        return Inventory.stream().id(id).first();
    }

}
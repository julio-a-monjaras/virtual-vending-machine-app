package com.techelevator;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class InventoryTest extends TestCase {

    Map<String, Item> items = new HashMap<>(Map.of(
            "B1", new Candy("B1", "Fancy Candy", 1.50),
            "B4", new Chips("B4", "Lays", 1.78),
            "A10", new Drink("A10", "Dr. Pepper", 1.42)
    )
    );
    Inventory inventory = new Inventory();

    @Test
    public void testGetFullInventory() {
        inventory.setFullInventory(items);
        assertEquals("Not the expected map", items, inventory.getFullInventory());
    }

    @Test
    public void testGetProduct() {
        inventory.setFullInventory(items);
        assertEquals("Not the expected product", items.get("B1"), inventory.getProduct("B1"));
    }
}
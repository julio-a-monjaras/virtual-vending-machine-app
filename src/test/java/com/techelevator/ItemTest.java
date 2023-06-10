package com.techelevator;

import junit.framework.TestCase;
import org.junit.Test;

public class ItemTest extends TestCase {

    @Test
    public void testTestToString1() {
        Item candy = new Candy("B1", "Moonpie", 1.80);

        assertEquals("Not the expected message", "B1 | Moonpie | $1.80", candy.toString());
    }

    @Test
    public void testTestToString2() {
        Item drink = new Drink("E2", "Dr. Salt", 1.50);

        assertEquals("Not the expected message", "E2 | Dr. Salt | $1.50", drink.toString());
    }

    @Test
    public void testTestToString3() {
        Item gum = new Gum("E7", "Gummy gum", 10.00);

        assertEquals("Not the expected message", "E7 | Gummy gum | $10.00", gum.toString());
    }

    @Test
    public void testTestToString4() {
        Item chip = new Chips("B10", "Lays potato chips", 3.40);

        assertEquals("Not the expected message", "B10 | Lays potato chips | $3.40", chip.toString());
    }
}
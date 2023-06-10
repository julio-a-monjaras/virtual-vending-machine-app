package com.techelevator;

import junit.framework.TestCase;
import org.junit.Test;

public class MoneyTest extends TestCase {

    @Test
    public void testGetChange1() {
        double amount = 1.78;

        assertEquals("Not the expected change", "Quarters: 7   Cents: 3   ", Money.getChange(amount));
    }

    @Test
    public void testGetChange2() {
        double amount = 2.84;

        assertEquals("Not the expected change", "Quarters: 11   Nickles: 1   Cents: 4   ", Money.getChange(amount));
    }
}
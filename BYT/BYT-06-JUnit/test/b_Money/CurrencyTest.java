package b_Money;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
    Currency SEK, DKK, NOK, EUR;

    @Before
    public void setUp() throws Exception {
        /* Setup currencies with exchange rates */
        SEK = new Currency("SEK", 0.15);
        DKK = new Currency("DKK", 0.20);
        EUR = new Currency("EUR", 1.5);
    }

    @Test
    public void testGetName() {
        Assert.assertEquals("Test: name of currency SEK", "SEK", SEK.getName());
        Assert.assertEquals("Test: name of currency DKK", "DKK", DKK.getName());
        Assert.assertEquals("Test: name of currency EUR", "EUR", EUR.getName());
    }

    @Test
    public void testGetRate() {
        Assert.assertEquals("Test: rate of currency SEK", Double.valueOf(0.15), SEK.getRate());
        Assert.assertEquals("Test: rate of currency DKK", Double.valueOf(0.20), DKK.getRate());
        Assert.assertEquals("Test: rate of currency EUR", Double.valueOf(1.5), EUR.getRate());
    }

    @Test
    public void testSetRate() {
        SEK.setRate(0.50);
        Assert.assertEquals("Test: setting rate of SEK", Double.valueOf(0.50), SEK.getRate());
    }

    @Test
    public void testUniversalValue() {
        Assert.assertEquals("Test: conversion to universal value SEK", Integer.valueOf(15), SEK.universalValue(100));
        Assert.assertEquals("Test: conversion to universal value DKK", Integer.valueOf(20), DKK.universalValue(100));
        Assert.assertEquals("Test: conversion to universal value EUR", Integer.valueOf(150), EUR.universalValue(100));

    }

    @Test
    public void testValueInThisCurrency() {
        Assert.assertEquals("Test: conversion to different currency (SEK->EUR)", Integer.valueOf(10), SEK.valueInThisCurrency(100, EUR));
        Assert.assertEquals("Test: conversion to different currency (EUR->SEK)", Integer.valueOf(100), EUR.valueInThisCurrency(10, SEK));
    }

}

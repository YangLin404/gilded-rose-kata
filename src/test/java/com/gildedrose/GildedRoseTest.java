package com.gildedrose;

import com.gildedrose.decorator.factory.DecoratorFactory;
import com.gildedrose.exception.InvalidItemException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GildedRoseTest {

    private static final String STANDARD_ITEM_NAME = "test";
    private static final int POSITIVE_SELL_IN_DAYS = 10;
    private static final int NOT_MAX_QUALITY = 10;
    private static final int MAX_QUALITY = 50;
    private static final int ALMOST_MAX_QUALITY = 49;
    private static final int LEGENDARY_QUALITY = 80;

    @Test
    void GildedRoseShouldThrownInvalidItemExceptionWhenItemListIsNull() {
        assertThrows(InvalidItemException.class, () -> {
            GildedRose gildedRose = new GildedRose(null);
        });
    }

    @Test
    void GildedRoseShouldThrownInvalidItemExceptionWhenItemsListContainsNullObject() {
        assertThrows(InvalidItemException.class, () -> {
            GildedRose gildedRose = new GildedRose(new Item[] {null});
        });
    }

    @Test
    void standardItemSellInShouldDecreaseBy1EachDay() throws InvalidItemException {
        GildedRose app = aGildedRose(aStandardItem(POSITIVE_SELL_IN_DAYS, NOT_MAX_QUALITY));

        app.updateQuality();

        assertThat(getSellIn(app), is(POSITIVE_SELL_IN_DAYS - 1));
    }

    @Test
    void standardItemQualityShouldDecreaseBy1WhenSellInIsAbove0() throws InvalidItemException {
        GildedRose app = aGildedRose(aStandardItem(POSITIVE_SELL_IN_DAYS, NOT_MAX_QUALITY));

        app.updateQuality();

        assertThat(getQuality(app), is(NOT_MAX_QUALITY - 1));
    }

    @Test
    void standardItemQualityShouldDecreaseBy2WhenSellInIs0OrLess() throws InvalidItemException {
        GildedRose app = aGildedRose(aStandardItem(0, NOT_MAX_QUALITY));

        app.updateQuality();

        assertThat(getQuality(app), is(NOT_MAX_QUALITY - 2));
    }

    @Test
    void standardItemQualityShouldNeverBeNegative() throws InvalidItemException {
        GildedRose app = aGildedRose(aStandardItem(POSITIVE_SELL_IN_DAYS, 0));

        app.updateQuality();

        assertThat(getQuality(app), is(0));
    }

    @Test
    void agedBrieItemSellInShouldDecreaseBy1EachDay() throws InvalidItemException {
        GildedRose app = aGildedRose(aAgedBrieItem(POSITIVE_SELL_IN_DAYS, NOT_MAX_QUALITY));

        app.updateQuality();

        assertThat(getSellIn(app), is(POSITIVE_SELL_IN_DAYS - 1));
    }

    @Test
    void agedBrieShouldIncreaseQualityBy1EachDay() throws InvalidItemException {
        GildedRose app = aGildedRose(aAgedBrieItem(POSITIVE_SELL_IN_DAYS, NOT_MAX_QUALITY));

        app.updateQuality();

        assertThat(getQuality(app), is(NOT_MAX_QUALITY + 1));
    }

    @Test
    void agedBrieShouldIncreaseQualityBy1WhenSellInLessThan0() throws InvalidItemException {
        GildedRose app = aGildedRose(aAgedBrieItem(0, NOT_MAX_QUALITY));

        app.updateQuality();

        assertThat(getQuality(app), is(NOT_MAX_QUALITY + 2));
    }

    @Test
    void agedBriesQualityShouldNotGoAbove50WhenSellInMoreThan0() throws InvalidItemException {
        GildedRose app = aGildedRose(aAgedBrieItem(POSITIVE_SELL_IN_DAYS, ALMOST_MAX_QUALITY));

        app.updateQuality();
        app.updateQuality();

        assertThat(getQuality(app), is(MAX_QUALITY));
    }

    @Test
    void agedBriesQualityShouldNotGoAbove50WhenSellInIsOrLessThan0() throws InvalidItemException {
        GildedRose app = aGildedRose(aAgedBrieItem(0, ALMOST_MAX_QUALITY));

        app.updateQuality();
        app.updateQuality();

        assertThat(getQuality(app), is(MAX_QUALITY));
    }

    @Test
    void agedBriesQualityShouldNeverBeNegative() throws InvalidItemException {
        GildedRose app = aGildedRose(aAgedBrieItem(POSITIVE_SELL_IN_DAYS, 0));

        app.updateQuality();

        assertThat(getQuality(app), greaterThan(0));
    }

    @Test
    void legendaryItemShouldNeverBeSold() throws InvalidItemException {
        GildedRose app = aGildedRose(aSulfuras());

        app.updateQuality();

        assertThat(getSellIn(app), is(POSITIVE_SELL_IN_DAYS));
    }

    @Test
    void legendaryItemQualityShouldNeverBeChanged() throws InvalidItemException {
        GildedRose app = aGildedRose(aSulfuras());

        app.updateQuality();

        assertThat(getQuality(app), is(LEGENDARY_QUALITY));
    }

    @Test
    void backstagePassesSellInShouldDecreaseBy1EachDay() throws InvalidItemException {
        GildedRose app = aGildedRose(aBackstagePasses(POSITIVE_SELL_IN_DAYS, NOT_MAX_QUALITY));

        app.updateQuality();

        assertThat(getSellIn(app), is(POSITIVE_SELL_IN_DAYS - 1));
    }

    @Test
    void backstagePassesShouldIncreaseQualityBy1WhenSellInMoreThan10Days() throws InvalidItemException {
        GildedRose app = aGildedRose(aBackstagePasses(15, NOT_MAX_QUALITY));

        app.updateQuality();

        assertThat(getQuality(app), is(NOT_MAX_QUALITY + 1));
    }

    @Test
    void backstagePassesShouldIncreaseQualityBy2WhenSellInLessThan11DaysButMoreThan5Days() throws InvalidItemException {
        GildedRose app = aGildedRose(aBackstagePasses(10, NOT_MAX_QUALITY));

        app.updateQuality();

        assertThat(getQuality(app), is(NOT_MAX_QUALITY + 2));
    }

    @Test
    void backstagePassesShouldIncreaseQualityBy3WhenSellInIs5DaysOrLess() throws InvalidItemException {
        GildedRose app = aGildedRose(aBackstagePasses(5, NOT_MAX_QUALITY));

        app.updateQuality();

        assertThat(getQuality(app), is(NOT_MAX_QUALITY + 3));
    }

    @Test
    void backstagePassesQualityShouldDropTo0AfterConcert() throws InvalidItemException {
        GildedRose app = aGildedRose(aBackstagePasses(0, MAX_QUALITY));

        app.updateQuality();

        assertThat(getQuality(app), is(0));
    }

    @Test
    void backstagePassesQualityShouldNotGoAbove50WhenSellInMoreThan10Days() throws InvalidItemException {
        GildedRose app = aGildedRose(aBackstagePasses(15, MAX_QUALITY));

        app.updateQuality();

        assertThat(getQuality(app), is(MAX_QUALITY));
    }

    @Test
    void backstagePassesQualityShouldNotGoAbove50WhenSellInLessThan11DaysButMoreThan5Days() throws InvalidItemException {
        GildedRose app = aGildedRose(aBackstagePasses(10, MAX_QUALITY));

        app.updateQuality();

        assertThat(getQuality(app), is(MAX_QUALITY));
    }

    @Test
    void backstagePassesQualityShouldNotGoAbove50WhenSellInIs5DaysOrLess() throws InvalidItemException {
        GildedRose app = aGildedRose(aBackstagePasses(5, MAX_QUALITY));

        app.updateQuality();

        assertThat(getQuality(app), is(MAX_QUALITY));
    }
    @Test
    void conjuredItemSellInShouldDecreaseBy1EachDay() throws InvalidItemException {
        GildedRose app = aGildedRose(aConjuredItem(POSITIVE_SELL_IN_DAYS, NOT_MAX_QUALITY));

        app.updateQuality();

        assertThat(getSellIn(app), is(POSITIVE_SELL_IN_DAYS - 1));
    }

    @Test
    void conjuredItemQualityShouldDecreaseBy2WhenSellInIsAbove0() throws InvalidItemException {
        GildedRose app = aGildedRose(aConjuredItem(POSITIVE_SELL_IN_DAYS, NOT_MAX_QUALITY));

        app.updateQuality();

        assertThat(getQuality(app), is(NOT_MAX_QUALITY - 2));
    }

    @Test
    void conjuredItemQualityShouldDecreaseBy4WhenSellInIs0OrLess() throws InvalidItemException {
        GildedRose app = aGildedRose(aConjuredItem(0, NOT_MAX_QUALITY));

        app.updateQuality();

        assertThat(getQuality(app), is(NOT_MAX_QUALITY - 4));
    }

    @Test
    void conjuredItemQualityShouldNeverBeNegative() throws InvalidItemException {
        GildedRose app = aGildedRose(aConjuredItem(POSITIVE_SELL_IN_DAYS, 0));

        app.updateQuality();

        assertThat(getQuality(app), is(0));
    }

    private Item aItem(String name, int sellIn, int quality) {
        return new Item(name, sellIn, quality);
    }

    private Item aStandardItem(int sellIn, int quality) {
        return aItem(STANDARD_ITEM_NAME, sellIn, quality);
    }

    private Item aAgedBrieItem(int sellIn, int quality) {
        return aItem(DecoratorFactory.AGED_BRIE, sellIn, quality);
    }

    private Item aSulfuras() {
        return aItem(DecoratorFactory.SULFURAS, GildedRoseTest.POSITIVE_SELL_IN_DAYS, GildedRoseTest.LEGENDARY_QUALITY);
    }

    private Item aBackstagePasses(int sellIn, int quality) {
        return aItem(DecoratorFactory.BACKSTAGE_PASSES, sellIn, quality);
    }

    private Item aConjuredItem(int sellIn, int quality) {
        return aItem(DecoratorFactory.CONJURED, sellIn, quality);
    }

    private GildedRose aGildedRose(Item... items) throws InvalidItemException {
        return new GildedRose(items);
    }

    private int getQuality(GildedRose gildedRose) {
        return gildedRose.items[0].quality;
    }

    private int getSellIn(GildedRose gildedRose) {
        return gildedRose.items[0].sellIn;
    }

}

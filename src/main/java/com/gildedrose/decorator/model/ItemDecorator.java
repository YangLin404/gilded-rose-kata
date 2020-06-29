package com.gildedrose.decorator.model;

import com.gildedrose.Item;

public abstract class ItemDecorator {

    private final int MAX_QUALITY = 50;
    protected final int MIN_QUALITY = 0;

    protected final Item item;

    public ItemDecorator(Item item) {
        this.item = item;
    }

    public void update() {
        updateSellIn();
        updateQuality();
        verifyQuality();
    }

    protected void updateSellIn() {
        item.sellIn--;
    }

    protected abstract void updateQuality();

    protected void increaseQualityBy(int value) {
        item.quality += value;
    }

    protected void decreaseQualityBy(int value) {
        item.quality -= value;
    }

    protected boolean hasSellByDatePassed() {
        return getSellIn() < 0;
    }

    protected int getSellIn() {
        return item.sellIn;
    }

    protected void setQuality(int quality) {
        item.quality = quality;
    }

    protected int getQuality() {
        return item.quality;
    }

    protected void verifyQuality() {
        if (getQuality() > MAX_QUALITY) {
            setQuality(MAX_QUALITY);
        } else if (getQuality() < MIN_QUALITY) {
            setQuality(MIN_QUALITY);
        }
    }
}

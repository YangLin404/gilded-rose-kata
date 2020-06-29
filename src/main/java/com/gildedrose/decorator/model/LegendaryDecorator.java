package com.gildedrose.decorator.model;

import com.gildedrose.Item;

public class LegendaryDecorator extends ItemDecorator {

    private final int LEGENDARY_QUALITY = 80;

    public LegendaryDecorator(Item item) {
        super(item);
    }

    @Override
    protected void updateSellIn() { }

    @Override
    protected void updateQuality() {
        setQuality(LEGENDARY_QUALITY);
    }

    @Override
    protected void verifyQuality() {
        item.quality = LEGENDARY_QUALITY;
    }
}

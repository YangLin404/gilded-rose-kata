package com.gildedrose.decorator.model;

import com.gildedrose.Item;

public class AgedBrieDecorator extends ItemDecorator {

    public AgedBrieDecorator(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        increaseQualityBy(hasSellByDatePassed() ? 2 : 1);
    }
}

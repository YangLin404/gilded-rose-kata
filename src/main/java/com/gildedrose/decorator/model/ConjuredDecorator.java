package com.gildedrose.decorator.model;

import com.gildedrose.Item;

public class ConjuredDecorator extends ItemDecorator {

    public ConjuredDecorator(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        decreaseQualityBy(hasSellByDatePassed() ? 4 : 2);
    }
}

package com.gildedrose.decorator.model;

import com.gildedrose.Item;

public class StandardDecorator extends ItemDecorator {
    public StandardDecorator(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        decreaseQualityBy(hasSellByDatePassed() ? 2 : 1);
    }
}

package com.gildedrose.decorator.model;

import com.gildedrose.Item;

public class BackstagePassesDecorator extends ItemDecorator {

    public BackstagePassesDecorator(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        if (hasSellByDatePassed()) {
            setQuality(MIN_QUALITY);
        } else if (getSellIn() <= 5) {
            increaseQualityBy(3);
        } else if (getSellIn() <= 10) {
            increaseQualityBy(2);
        } else {
            increaseQualityBy(1);
        }

    }
}

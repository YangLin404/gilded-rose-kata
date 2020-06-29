package com.gildedrose.decorator.model;

import com.gildedrose.Item;

public class EmptyDecorator extends ItemDecorator {
    public EmptyDecorator(Item item) {
        super(item);
    }

    @Override
    public void update() { }

    @Override
    protected void updateQuality() { }
}

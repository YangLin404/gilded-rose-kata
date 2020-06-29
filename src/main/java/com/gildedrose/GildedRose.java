package com.gildedrose;

import com.gildedrose.decorator.factory.DecoratorFactory;
import com.gildedrose.decorator.model.ItemDecorator;
import com.gildedrose.exception.InvalidItemException;

import java.util.Arrays;
import java.util.Objects;

import static java.util.Objects.isNull;

class GildedRose {
    Item[] items;
    DecoratorFactory decoratorFactory;

    public GildedRose(Item[] items) throws InvalidItemException {
        validateItems(items);
        this.items = items;
        decoratorFactory = new DecoratorFactory();
    }

    public void updateQuality() {
        Arrays.stream(items)
                .map(item -> decoratorFactory.createDecorator(item))
                .forEach(ItemDecorator::update);
    }

    public void validateItems(Item[] items) throws InvalidItemException {
        if (isNull(items) || Arrays.stream(items).anyMatch(Objects::isNull)) {
            throw new InvalidItemException();
        }
    }
}

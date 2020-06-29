package com.gildedrose.decorator.factory;

import com.gildedrose.Item;
import com.gildedrose.decorator.model.*;
import com.gildedrose.exception.InvalidItemException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.util.Objects.isNull;

public class DecoratorFactory {

    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured Mana Cake";

    private final Map<String, Function<Item, ItemDecorator>> itemDecorators = new HashMap<>();

    public DecoratorFactory() {
        itemDecorators.put(AGED_BRIE, AgedBrieDecorator::new);
        itemDecorators.put(BACKSTAGE_PASSES, BackstagePassesDecorator::new);
        itemDecorators.put(SULFURAS, LegendaryDecorator::new);
        itemDecorators.put(CONJURED, ConjuredDecorator::new);
    }

    public ItemDecorator createDecorator(Item item) {
        if (isNull(item)) {
            return new EmptyDecorator(null);
        }
        return isStandardItem(item) ? new StandardDecorator(item) : itemDecorators.get(item.name).apply(item);
    }

    private boolean isStandardItem(Item item) {
        return !itemDecorators.containsKey(item.name);
    }

}

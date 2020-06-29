package com.gildedrose.decorator.factory;

import com.gildedrose.Item;
import com.gildedrose.decorator.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

class DecoratorFactoryTest {
    private DecoratorFactory decoratorFactory;
    private final Item STANDARD_ITEM = new Item("test", 1, 1);
    private final Item AGED_BRIE_ITEM = new Item("Aged Brie", 1, 1);
    private final Item BACKSTAGE_PASSES_ITEM = new Item("Backstage passes to a TAFKAL80ETC concert", 1, 1);
    private final Item LEGENDARY_SULFURAS_ITEM = new Item("Sulfuras, Hand of Ragnaros", 1, 80);
    private final Item CONJURED_ITEM = new Item("Conjured Mana Cake", 1, 1);

    @BeforeEach
    void beforeEach() {
        decoratorFactory = new DecoratorFactory();
    }

    @Test
    void standardDecoratorShouldBeCreatedForStandardItem() {
        ItemDecorator itemDecorator = decoratorFactory.createDecorator(STANDARD_ITEM);

        assertThat(itemDecorator, instanceOf(StandardDecorator.class));
    }

    @Test
    void agedBrieDecoratorShouldBeCreatedForAgedBrieItem() {
        ItemDecorator itemDecorator = decoratorFactory.createDecorator(AGED_BRIE_ITEM);

        assertThat(itemDecorator, instanceOf(AgedBrieDecorator.class));
    }

    @Test
    void backstagePassesDecoratorShouldBeCreatedForBackstagePassesItem() {
        ItemDecorator itemDecorator = decoratorFactory.createDecorator(BACKSTAGE_PASSES_ITEM);

        assertThat(itemDecorator, instanceOf(BackstagePassesDecorator.class));
    }

    @Test
    void legendaryItemDecoratorShouldBeCreatedForLegendaryItem() {
        ItemDecorator itemDecorator = decoratorFactory.createDecorator(LEGENDARY_SULFURAS_ITEM);

        assertThat(itemDecorator, instanceOf(LegendaryDecorator.class));
    }

    @Test
    void conjuredItemDecoratorShouldBeCreatedForConjuredItem() {
        ItemDecorator itemDecorator = decoratorFactory.createDecorator(CONJURED_ITEM);

        assertThat(itemDecorator, instanceOf(ConjuredDecorator.class));
    }

    @Test
    void EmptyDecoratorShouldBeCreatedForNullItem() {
        ItemDecorator itemDecorator = decoratorFactory.createDecorator(null);

        assertThat(itemDecorator, instanceOf(EmptyDecorator.class));
    }
}

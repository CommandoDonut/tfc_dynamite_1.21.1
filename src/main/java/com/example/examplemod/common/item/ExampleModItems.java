package com.example.examplemod.common.item;

import com.example.examplemod.ExampleMod;
import net.neoforged.neoforge.registries.*;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.Item.Properties;

import java.util.function.*;

public final class ExampleModItems {

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExampleMod.MOD_ID);

	public static final DeferredItem<ExampleItem> EXAMPLE_ITEM = register("example_item", ExampleItem::new);
	public static final DeferredItem<Item> EXAMPLE_SIMPLE_ITEM = registerSimple("example_simple_item");
	public static final DeferredItem<Item> EXAMPLE_SIMPLE_ITEM_WITH_CUSTOM_PROPERTIES = registerSimple("example_simple_item_with_custom_properties",
			properties -> properties.fireResistant()
					.stacksTo(5)
					.durability(42));
	public static final DeferredItem<ExampleItem> EXAMPLE_ITEM_WITH_CUSTOM_PROPERTIES = register("example_item_with_custom_properties",
			ExampleItem::new,
			properties -> properties.stacksTo(42)
					.jukeboxPlayable(JukeboxSongs.PIGSTEP)
					.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true));

	private static DeferredItem<Item> registerSimple(final String name) {
		return register(name, Item::new);
	}

	private static DeferredItem<Item> registerSimple(final String name, final UnaryOperator<Properties> properties) {
		return register(name, Item::new, properties);
	}

	private static <I extends Item> DeferredItem<I> register(final String name, final Function<Properties, ? extends I> itemFactory) {
		return register(name, itemFactory, UnaryOperator.identity());
	}

	private static <I extends Item> DeferredItem<I> register(final String name, final Function<Properties, ? extends I> itemFactory,
			final UnaryOperator<Properties> properties) {
		return ITEMS.register(name, () -> itemFactory.apply(properties.apply(new Properties())));
	}
}
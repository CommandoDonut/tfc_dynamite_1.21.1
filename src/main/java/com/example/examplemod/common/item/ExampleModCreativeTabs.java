package com.example.examplemod.common.item;

import com.example.examplemod.ExampleMod;
import net.neoforged.neoforge.registries.*;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.CreativeModeTab.DisplayItemsGenerator;

import java.util.function.Supplier;

public final class ExampleModCreativeTabs {

	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
			ExampleMod.MOD_ID);

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = register("example_tab", ExampleModItems.EXAMPLE_ITEM::toStack,
			(parameters, output) -> {
				// For most addons adding everything in your Item Registry makes sense
				ExampleModItems.ITEMS.getEntries().stream().map(DeferredHolder::get).forEach(output::accept);
				// You can also add stacks with custom data
				final var stack = new ItemStack(Items.DIRT);
				stack.set(DataComponents.CUSTOM_NAME, Component.literal("I am dirt"));
				output.accept(stack);
			});

	private static DeferredHolder<CreativeModeTab, CreativeModeTab> register(final String name, final Supplier<ItemStack> icon,
			final DisplayItemsGenerator displayItems) {
		return CREATIVE_MODE_TABS.register(name, registryName -> CreativeModeTab.builder()
				.icon(icon)
				.title(Component.translatable(registryName.toLanguageKey("itemGroup")))
				.displayItems(displayItems)
				.build());
	}
}
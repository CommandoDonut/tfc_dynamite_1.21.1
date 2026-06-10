package com.example.examplemod.providers;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.common.block.ExampleModBlocks;
import com.example.examplemod.common.item.*;
import mod.traister101.datagenutils.data.EnhancedLanguageProvider;
import mod.traister101.datagenutils.data.language.*;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class BuiltInLanguage extends EnhancedLanguageProvider {

	public BuiltInLanguage(final PackOutput output, final CompletableFuture<Provider> registries) {
		super(output, registries, ExampleMod.MOD_ID, "en_us", List.of(new Items(), new Blocks(), new CreativeTabs()));
	}

	@Override
	protected void addTranslations() {
		add(ExampleItem.EXAMPLE_TOOLTIP, "Example Tooltip");
	}

	private static final class Items extends ItemLanguageProvider {

		Items() {super(ExampleModItems.ITEMS);}

		@Override
		protected void addTranslations(final LanguageOutput<Item> output) {
			// Same as output.add(ExampleModItems.EXAMPLE_ITEM, "Example Item"));
			output.simple(ExampleModItems.EXAMPLE_ITEM);
			output.simple(ExampleModItems.EXAMPLE_SIMPLE_ITEM);
			output.simple(ExampleModItems.EXAMPLE_SIMPLE_ITEM_WITH_CUSTOM_PROPERTIES);
			output.simple(ExampleModItems.EXAMPLE_ITEM_WITH_CUSTOM_PROPERTIES);
		}
	}

	private static final class Blocks extends BlockLanguageProvider {

		Blocks() {super(ExampleModBlocks.BLOCKS);}

		@Override
		protected void addTranslations(final LanguageOutput<Block> output) {
			output.simple(ExampleModBlocks.EXAMPLE_BLOCK);
			output.simple(ExampleModBlocks.EXAMPLE_BLOCK_WITHOUT_ITEM);
			output.simple(ExampleModBlocks.EXAMPLE_STAIRS);
		}
	}

	private static final class CreativeTabs extends CreativeTabLanguageProvider {

		CreativeTabs() {super(ExampleModCreativeTabs.CREATIVE_MODE_TABS);}

		@Override
		protected void addTranslations(final LanguageOutput<CreativeModeTab> output) {
			output.simple(ExampleModCreativeTabs.EXAMPLE_TAB);
		}
	}
}
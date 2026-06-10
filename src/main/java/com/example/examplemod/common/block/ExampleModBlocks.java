package com.example.examplemod.common.block;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.common.item.ExampleModItems;
import net.neoforged.neoforge.registries.*;

import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

import java.util.function.*;

public final class ExampleModBlocks {

	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ExampleMod.MOD_ID);

	public static final DeferredBlock<Block> EXAMPLE_BLOCK = registerSimple("example_block");
	// You basically always want to also register an item for your blocks since the related Item is what is actually held by players
	public static final DeferredBlock<Block> EXAMPLE_BLOCK_WITHOUT_ITEM = registerSimpleNoItem("example_block_without_item");
	public static final DeferredBlock<StairBlock> EXAMPLE_STAIRS = registerSimpleItem("example_stairs",
			properties -> new StairBlock(Blocks.DIRT.defaultBlockState(), properties));

	private static DeferredBlock<Block> registerSimple(final String name) {
		return registerSimple(name, UnaryOperator.identity());
	}

	private static DeferredBlock<Block> registerSimple(final String name, final UnaryOperator<Properties> properties) {
		return register(name, Block::new, properties, BlockItem::new, UnaryOperator.identity());
	}

	private static DeferredBlock<Block> registerSimple(final String name, final Supplier<Properties> properties) {
		return registerSimpleItem(name, Block::new, properties);
	}

	private static <B extends Block> DeferredBlock<B> registerSimpleItem(final String name, final Function<Properties, ? extends B> blockFactory) {
		return registerSimpleItem(name, blockFactory, UnaryOperator.identity());
	}

	private static <B extends Block> DeferredBlock<B> registerSimpleItem(final String name, final Function<Properties, ? extends B> blockFactory,
			final UnaryOperator<Properties> blockProperties) {
		return register(name, blockFactory, blockProperties, BlockItem::new, UnaryOperator.identity());
	}

	private static <B extends Block> DeferredBlock<B> registerSimpleItem(final String name, final Function<Properties, ? extends B> blockFactory,
			final Supplier<Properties> blockProperties) {
		return register(name, blockFactory, blockProperties, BlockItem::new, UnaryOperator.identity());
	}

	private static <B extends Block> DeferredBlock<B> register(final String name, final Function<Properties, ? extends B> blockFactory,
			final UnaryOperator<Properties> blockProperties, final BiFunction<B, Item.Properties, ? extends BlockItem> itemFactory,
			final UnaryOperator<Item.Properties> itemProperties) {
		return register(name, blockFactory, () -> blockProperties.apply(Properties.of()), itemFactory, itemProperties);
	}

	private static <B extends Block> DeferredBlock<B> register(final String name, final Function<Properties, ? extends B> blockFactory,
			final Supplier<Properties> blockProperties, final BiFunction<B, Item.Properties, ? extends BlockItem> itemFactory,
			final UnaryOperator<Item.Properties> itemProperties) {
		final DeferredBlock<B> block = registerNoItem(name, blockFactory, blockProperties);
		ExampleModItems.ITEMS.register(name, () -> itemFactory.apply(block.get(), itemProperties.apply(new Item.Properties())));
		return block;
	}

	private static DeferredBlock<Block> registerSimpleNoItem(final String name) {
		return registerSimpleNoItem(name, UnaryOperator.identity());
	}

	private static DeferredBlock<Block> registerSimpleNoItem(final String name, final UnaryOperator<Properties> properties) {
		return registerNoItem(name, Block::new, properties);
	}

	private static DeferredBlock<Block> registerSimpleNoItem(final String name, final Supplier<Properties> properties) {
		return registerNoItem(name, Block::new, properties);
	}

	private static <B extends Block> DeferredBlock<B> registerNoItem(final String name, final Function<Properties, ? extends B> factory) {
		return registerNoItem(name, factory, UnaryOperator.identity());
	}

	private static <B extends Block> DeferredBlock<B> registerNoItem(final String name, final Function<Properties, ? extends B> factory,
			final UnaryOperator<Properties> properties) {
		return registerNoItem(name, factory, () -> properties.apply(Properties.of()));
	}

	private static <B extends Block> DeferredBlock<B> registerNoItem(final String name, final Function<Properties, ? extends B> factory,
			final Supplier<Properties> properties) {
		return BLOCKS.register(name, () -> factory.apply(properties.get()));
	}
}
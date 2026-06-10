package com.example.examplemod.providers.models;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.common.block.ExampleModBlocks;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import net.minecraft.data.PackOutput;

public class BuiltInBlockStates extends BlockStateProvider {

	public BuiltInBlockStates(final PackOutput output, final ExistingFileHelper existingFileHelper) {
		super(output, ExampleMod.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(ExampleModBlocks.EXAMPLE_BLOCK.get());
		simpleBlock(ExampleModBlocks.EXAMPLE_BLOCK_WITHOUT_ITEM.get());
		stairsBlock(ExampleModBlocks.EXAMPLE_STAIRS.get(), mcLoc("block/debug2"));
	}
}
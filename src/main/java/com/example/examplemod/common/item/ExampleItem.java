package com.example.examplemod.common.item;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.network.ExampleServerboundPacket;
import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.network.chat.Component;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class ExampleItem extends Item {

	public static final String EXAMPLE_TOOLTIP = ExampleMod.lang("tooltip.example_item.example_tooltip");

	public ExampleItem(final Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(final Level level, final Player player, final InteractionHand usedHand) {
		if (level.isClientSide) {
			PacketDistributor.sendToServer(new ExampleServerboundPacket(Component.literal("This component went over the network")));
			return InteractionResultHolder.consume(player.getItemInHand(usedHand));
		}
		return InteractionResultHolder.success(player.getItemInHand(usedHand));
	}

	@Override
	public void appendHoverText(final ItemStack stack, final TooltipContext context, final List<Component> tooltipComponents,
			final TooltipFlag tooltipFlag) {
		super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
		tooltipComponents.add(Component.translatable(EXAMPLE_TOOLTIP));
	}
}
package com.example.examplemod.client;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import net.minecraft.network.chat.Component;

public final class ExampleModClientForgeEvents {

	public static void init(final IEventBus eventBus) {
		eventBus.addListener(ExampleModClientForgeEvents::onItemTooltip);
	}

	private static void onItemTooltip(final ItemTooltipEvent event) {
		event.getToolTip().add(Component.literal("This component was added via an event!"));
	}
}
package com.example.examplemod;

import net.neoforged.bus.api.*;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

public final class ExampleModForgeEvents {

	public static void init(final IEventBus eventBus) {
		// Register all static @SubscribeEvent annotated event methods
		eventBus.register(ExampleModForgeEvents.class);
	}

	@SubscribeEvent
	private static void onTick(final LevelTickEvent.Post event) {
		if (event.getLevel().getGameTime() % 100 != 0) {
			return;
		}
		ExampleMod.LOG.debug("This is a debug log every 100 ticks! (5 seconds)");
	}
}
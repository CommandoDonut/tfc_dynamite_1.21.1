package com.example.examplemod.client;

import net.neoforged.bus.api.*;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.neoforged.neoforge.client.gui.*;

public final class ExampleModClientEvents {

	public static void init(final ModContainer modContainer, final IEventBus modBus) {
		modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
		// Register just this one static method
		modBus.addListener(ExampleModClientEvents::clientSetup);
		// Register all static @SubscribeEvent annotated event methods
		modBus.register(ExampleModClientEvents.class);
	}

	private static void clientSetup(final FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			// Things like ItemProperties.register should be enqueued
		});
	}

	@SubscribeEvent
	public static void registerEntityRenderers(final RegisterRenderers event) {
		// Always check for a registration event. This one is for registering Entity Renderers.
		// All EntityTypes need a renderer, vanilla provides NoopRenderer if you truly do not want one at all
	}
}
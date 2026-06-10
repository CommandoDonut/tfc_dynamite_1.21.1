package com.example.examplemod.network;

import com.example.examplemod.ExampleMod;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadHandler;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.*;

public final class ExampleModPackets {

	public static void init(final RegisterPayloadHandlersEvent event) {
		final var registrar = event.registrar(ModList.get().getModFileById(ExampleMod.MOD_ID).versionString());

		// Client -> Server
		registrar.playToServer(ExampleServerboundPacket.TYPE, ExampleServerboundPacket.CODEC, onServer(ExampleServerboundPacket::handle));

		// Server -> Client
		registrar.playToClient(ExampleClientboundPacket.TYPE, ExampleClientboundPacket.CODEC, onClient(ExampleClientboundPacket::handle));
	}

	private static <T extends CustomPacketPayload> IPayloadHandler<T> onClient(final Consumer<T> handler) {
		return (payload, context) -> context.enqueueWork(() -> handler.accept(payload));
	}

	private static <T extends CustomPacketPayload> IPayloadHandler<T> onServer(final BiConsumer<T, ServerPlayer> handler) {
		return (payload, context) -> context.enqueueWork(() -> handler.accept(payload, (ServerPlayer) context.player()));
	}
}
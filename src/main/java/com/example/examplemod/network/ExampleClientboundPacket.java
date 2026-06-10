package com.example.examplemod.network;

import com.example.examplemod.ExampleMod;
import io.netty.buffer.ByteBuf;
import net.dries007.tfc.client.ClientHelpers;

import net.minecraft.network.codec.*;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record ExampleClientboundPacket(int entityId) implements CustomPacketPayload {

	public static final Type<ExampleClientboundPacket> TYPE = new Type<>(ExampleMod.location("example_client_packet"));

	public static final StreamCodec<ByteBuf, ExampleClientboundPacket> CODEC = StreamCodec.composite(ByteBufCodecs.VAR_INT,
			ExampleClientboundPacket::entityId, ExampleClientboundPacket::new);

	void handle() {
		// Client code cannot be directly used in this sort of handle method
		final var level = ClientHelpers.getLevel();
		if (level == null) return;
		final var entity = level.getEntity(entityId);
		if (entity == ClientHelpers.getPlayer()) {
			ExampleMod.LOG.debug("We received a packet for our own player entity!");
		}
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
package com.example.examplemod.network;

import com.example.examplemod.ExampleMod;
import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.*;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;

public class ExampleServerboundPacket implements CustomPacketPayload {

	public static final CustomPacketPayload.Type<ExampleServerboundPacket> TYPE = new CustomPacketPayload.Type<>(
			ExampleMod.location("example_server_packet"));

	public static final StreamCodec<RegistryFriendlyByteBuf, ExampleServerboundPacket> CODEC = StreamCodec.composite(
			ComponentSerialization.STREAM_CODEC, exampleServerboundPacket -> exampleServerboundPacket.component, ExampleServerboundPacket::new);

	private final Component component;

	public ExampleServerboundPacket(final Component component) {
		this.component = component;
	}

	void handle(@Nullable final ServerPlayer player) {
		if (player == null) return;
		player.sendSystemMessage(component);
		PacketDistributor.sendToPlayer(player, new ExampleClientboundPacket(player.getId()));
	}

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
package com.ocelot.sound;

import com.ocelot.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

/**
 * @author Ocelot5836
 */
public class ModSounds {

	public static SoundEvent BLOCK_SAFE_LOCK;
	public static SoundEvent BLOCK_SAFE_UNLOCK;

	public static void register() {
		BLOCK_SAFE_LOCK = getRegisteredSound("block.safe.lock");
		BLOCK_SAFE_UNLOCK = getRegisteredSound("block.safe.unlock");
	}

	private static SoundEvent getRegisteredSound(String name) {
		ResourceLocation location = new ResourceLocation(Reference.MOD_ID, name);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(location);

		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
}
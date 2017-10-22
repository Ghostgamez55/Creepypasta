package com.ocelot.init;

import com.ocelot.Creepypasta;
import com.ocelot.Reference;
import com.ocelot.client.render.entity.RenderSmileDog;
import com.ocelot.client.render.entity.RenderTiccy;
import com.ocelot.entity.hostile.EntityTiccy;
import com.ocelot.entity.living.EntitySmileDog;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class ModEntities {

	/** The current entity id */
	private static int entityID = 0;

	public static void preInit() {
		registerEntities();
		registerTileEntities();
	}

	public static void init() {
		registerSpawns();
	}

	private static void registerTileEntities() {

	}

	/**
	 * Registers the specified entities.
	 */
	private static void registerEntities() {
		registerEntity(EntitySmileDog.class, "smile_dog", 32, 1, true, 0x639fff, 0x62ffe5);
		registerEntity(EntityTiccy.class, "ticcy", 32, 1, true, 0x639fff, 0x62ffe5);
	}

	/**
	 * Adds the entity's spawn location.
	 */
	public static void registerSpawns() {

	}

	/**
	 * Registers the entity renders
	 */
	public static void registerEntityRenders() {
		Minecraft mc = Minecraft.getMinecraft();
		RenderingRegistry.registerEntityRenderingHandler(EntitySmileDog.class, new RenderSmileDog(mc.getRenderManager()));
		RenderingRegistry.registerEntityRenderingHandler(EntityTiccy.class, new RenderTiccy(mc.getRenderManager()));
	}

	/**
	 * Registers an entity and an egg.
	 * 
	 * @param entityClass
	 *            The entity class
	 * @param entityName
	 *            A unique name for the entity
	 * @param id
	 *            A mod specific ID for the entity
	 * @param mod
	 *            The mod
	 * @param trackingRange
	 *            The range at which MC will send tracking updates
	 * @param updateFrequency
	 *            The frequency of tracking updates
	 * @param sendsVelocityUpdates
	 *            Whether to send velocity information packets as well
	 * @param primaryEggColor
	 *            The color of the main part of the spawn egg
	 * @param secondaryEggColor
	 *            The color of the secondary part of the spawn egg
	 */
	private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int primaryEggColor, int secondaryEggColor) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, entityName), entityClass, entityName, entityID++, Creepypasta.instance, trackingRange, updateFrequency, sendsVelocityUpdates, primaryEggColor, secondaryEggColor);
	}

	/**
	 * Registers an entity.
	 * 
	 * @param entityClass
	 *            The entity class
	 * @param entityName
	 *            A unique name for the entity
	 * @param id
	 *            A mod specific ID for the entity
	 * @param mod
	 *            The mod
	 * @param trackingRange
	 *            The range at which MC will send tracking updates
	 * @param updateFrequency
	 *            The frequency of tracking updates
	 * @param sendsVelocityUpdates
	 *            Whether to send velocity information packets as well
	 */
	private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, entityName), entityClass, entityName, entityID++, Creepypasta.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
	}
}
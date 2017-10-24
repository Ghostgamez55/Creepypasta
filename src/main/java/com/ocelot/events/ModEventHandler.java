package com.ocelot.events;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author Ocelot5836
 */
public class ModEventHandler {

	@SubscribeEvent
	public void onLivingUpdateEvent(LivingUpdateEvent event) {
		// World world = event.getEntity().world;
		// if (event.getEntity() instanceof EntityPlayer) {
		// EntityPlayer player = (EntityPlayer) event.getEntity();
		// boolean hasSmileDog = (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == ModItems.SMILE_DOG) || (player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() == ModItems.SMILE_DOG);
		// if (hasSmileDog && !world.isRemote) {
		// if (world.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(new BlockPos(player.posX, player.posY, player.posZ))).size() < 1) {
		// world.spawnEntity(new EntitySmileDog(world, player.posX, player.posY, player.posZ));
		// }
		// }
		// }
	}

	public void onRenderGameOverlayEvent(RenderGameOverlayEvent event) {
		ElementType type = event.getType();
		if (type == ElementType.EXPERIENCE) {

		}
	}
}
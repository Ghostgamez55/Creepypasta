package com.ocelot.events;

import com.ocelot.effect.particle.EntityBleedingParticle;
import com.ocelot.init.ModPotions;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author Ocelot5836
 */
public class ModEventHandler {

	/**
	 * TODO add the ability to spawn smile dog when holding his image
	 */
	@SubscribeEvent
	public void onLivingUpdateEvent(LivingUpdateEvent event) {
		World world = event.getEntity().world;
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			// boolean hasSmileDog = (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == ModItems.SMILE_DOG) || (player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() == ModItems.SMILE_DOG);
			// if (hasSmileDog && !world.isRemote) {
			// if (world.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(new BlockPos(player.posX, player.posY, player.posZ))).size() < 1) {
			// world.spawnEntity(new EntitySmileDog(world, player.posX, player.posY, player.posZ));
			// }
			// }

			if (player.isPotionActive(ModPotions.MISSING_KIDNEY)) {
				Minecraft.getMinecraft().effectRenderer.addEffect(new EntityBleedingParticle(world, player.posX-0.75+world.rand.nextFloat()*1.5f, player.posY+world.rand.nextFloat()*world.rand.nextInt(3), player.posZ-0.75+world.rand.nextFloat()*1.5f, 0, -0.2f, 0));
			}
		}
	}
}
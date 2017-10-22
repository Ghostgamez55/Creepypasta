package com.ocelot.events;

import com.ocelot.entity.living.EntitySmileDog;
import com.ocelot.init.ModItems;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class OnEntityUpdateEvent {

	@SubscribeEvent
	public void onLivingUpdateEvent(LivingUpdateEvent event) {
		World world = event.getEntity().world;
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			boolean hasSmileDog = (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == ModItems.SMILE_DOG) || (player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() == ModItems.SMILE_DOG);
			if (hasSmileDog) {
				if(world.getEntities(EntitySmileDog.class, null).size() <= 0) {
					world.spawnEntity(new EntitySmileDog(world));
				}
			}
		}
	}
}
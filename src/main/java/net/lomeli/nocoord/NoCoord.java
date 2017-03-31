package net.lomeli.nocoord;

import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Iterator;

@Mod(modid = "nocoord", name = "No Coord", version = "@VERSION@")
public class NoCoord {

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		if(FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			MinecraftForge.EVENT_BUS.register(this);
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void renderOverlayEvent(RenderGameOverlayEvent.Text event) {
		if(FMLClientHandler.instance().getClient().thePlayer.capabilities.isCreativeMode) {
			return;
		}
		Iterator<String> it = event.getLeft().listIterator();
		while(it.hasNext()) {
			String value = it.next();
			if(value != null && (value.startsWith("XYZ:") || value.startsWith("Block:") || value.startsWith("Chunk:") || value.startsWith("Looking at:") || value.startsWith("Chunk-relative:")))
				it.remove();
		}
	}
}

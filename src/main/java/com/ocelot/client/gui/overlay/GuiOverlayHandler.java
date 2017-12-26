package com.ocelot.client.gui.overlay;

import cjminecraft.core.client.gui.GuiOverlay;

public class GuiOverlayHandler extends GuiOverlay {

	public GuiOverlayHandler() {
		overlays.add(new GuiOverlaySanity(this));
		// overlays.add(new GuiOverlayStatic(this));
	}
}
package cjminecraft.core.client.gui;

import cjminecraft.core.CJCore;
import cjminecraft.core.config.CJCoreConfig;
import cjminecraft.core.energy.EnergyData;
import cjminecraft.core.energy.EnergyUnits;
import cjminecraft.core.energy.EnergyUnits.EnergyUnit;
import cjminecraft.core.energy.EnergyUtils;
import cjminecraft.core.util.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;

/**
 * Draw a simple {@link EnergyBar} which represents the energy inside of a {@link TileEntity}. Please use {@link GuiBase}, this will be removed in the next update
 * 
 * @author CJMinecraft
 * @deprecated
 */
public class EnergyBar extends GuiButton {

	public static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation(CJCore.MODID, "textures/gui/widgets.png");
	public static final int DEFAULT_WIDTH = 18;
	public static final int DEFAULT_HEIGHT = 84;

	private int textureX;
	private int textureY;

	/**
	 * Store the energy and capacity in the {@link EnergyBar} - used for rendering
	 */
	public long energy;
	public long capacity;

	/**
	 * Initialize the {@link EnergyBar}
	 * 
	 * @param buttonId
	 *            The id of the button
	 * @param x
	 *            The x position of the {@link EnergyBar}
	 * @param y
	 *            The y position of the {@link EnergyBar}
	 * @param energy
	 *            The amount of energy in the {@link EnergyBar} (can be 0)
	 * @param capacity
	 *            The maximum amount of energy in the {@link EnergyBar} (can be 0)
	 */
	public EnergyBar(int buttonId, int x, int y, long energy, long capacity) {
		super(buttonId, x, y, "");
		this.width = DEFAULT_WIDTH;
		this.height = DEFAULT_HEIGHT;
		this.textureX = 0;
		this.textureY = 0;
		this.energy = energy;
		this.capacity = capacity;
	}

	/**
	 * Initialize the {@link EnergyBar}
	 * 
	 * @param buttonId
	 *            The id of the button
	 * @param x
	 *            The x position of the {@link EnergyBar}
	 * @param y
	 *            The y position of the {@link EnergyBar}
	 * @param width
	 *            The width of the {@link EnergyBar}
	 * @param height
	 *            The height of the {@link EnergyBar}
	 * @param energy
	 *            The amount of energy in the {@link EnergyBar} (can be 0)
	 * @param capacity
	 *            The maximum amount of energy in the {@link EnergyBar} (can be 0)
	 */
	public EnergyBar(int buttonId, int x, int y, int width, int height, long energy, long capacity) {
		super(buttonId, x, y, "");
		this.width = width >= DEFAULT_WIDTH ? DEFAULT_WIDTH : width;
		this.height = height >= DEFAULT_HEIGHT ? DEFAULT_HEIGHT : height;
		this.textureX = 0;
		this.textureY = 0;
		this.energy = energy;
		this.capacity = capacity;
	}

	/**
	 * Draw the button on the screen
	 */
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;

		// Outer rim of bar
		this.drawVerticalLine(x, y - 1, y + height, 0xFF373737);
		this.drawHorizontalLine(x + 1, x + width - 2, y, 0xFF373737);
		this.drawHorizontalLine(x + width - 1, x + width - 1, y, 0xFF8B8B8B);
		this.drawHorizontalLine(x, x, y + height, 0xFF8B8B8B);
		this.drawVerticalLine(x + width - 1, y, y + height, 0xFFFFFFFF);
		this.drawHorizontalLine(x + 1, x + width - 1, y + height, 0xFFE2E2E2);

		// Actual background energy bar
		mc.getTextureManager().bindTexture(DEFAULT_TEXTURE);
		float[] colour = EnergyUnits.REDSTONE_FLUX.getColour();
		GlStateManager.color(colour[0], colour[1], colour[2]);
		this.drawTexturedModalRect(x + 1, y + 1, textureX + 1 + (Math.abs(DEFAULT_WIDTH - width) / 2), textureY + 1, width - 2, height - 1);

		// The overlay to show the amount of energy in the {@link TileEntity}
		mc.getTextureManager().bindTexture(DEFAULT_TEXTURE);
		RenderUtils.resetColour();
		this.drawTexturedModalRect(x + 1, y + 1, textureX + 1 + (Math.abs(DEFAULT_WIDTH - width) / 2), textureY + 1, width - 2, height - getEnergyBarHeight() - 1);

		updateEnergyBar(EnergyUtils.getCachedEnergyData(CJCore.MODID));
	}

	/**
	 * Use this in the action performed to be able to cycle the {@link EnergyUnit} when you click the {@link EnergyBar}
	 * 
	 * @param mc
	 *            Instance of {@link Minecraft} used for the press sound
	 * @param te
	 *            The tile entity which is holding the block's energy
	 */
	public void actionPerformed(Minecraft mc, TileEntity te) {
		actionPerformed(mc);
	}

	/**
	 * Use this in the action performed to be able to cycle the {@link EnergyUnit} when you click the {@link EnergyBar}
	 * 
	 * @param mc
	 *            Instance of {@link Minecraft} used for the press sound
	 */
	public void actionPerformed(Minecraft mc) {
		EnergyUnit toBe = EnergyUnits.REDSTONE_FLUX.cycleUnit();
		this.energy = EnergyUtils.convertEnergy(EnergyUnits.REDSTONE_FLUX, toBe, energy);
		this.capacity = EnergyUtils.convertEnergy(EnergyUnits.REDSTONE_FLUX, toBe, capacity);
		EnergyUnits.REDSTONE_FLUX = toBe;
		CJCoreConfig.syncFromFields();
	}

	/**
	 * Update the values inside of the {@link EnergyBar} Should update every time the game is rendered
	 * 
	 * @param energy
	 *            The energy in the {@link EnergyBar}
	 * @param capacity
	 *            The maximum amount of energy in the {@link EnergyBar}
	 */
	public void updateEnergyBar(long energy, long capacity) {
		this.energy = energy;
		this.capacity = capacity;
	}

	/**
	 * Update the values inside of the {@link EnergyBar} Should update every time the game is rendered
	 * 
	 * @param data
	 *            Holds the data regarding the energy inside
	 */
	public void updateEnergyBar(EnergyData data) {
		if (data == null)
			return;
		this.energy = data.getEnergy();
		this.capacity = data.getCapacity();
	}

	/**
	 * Calculates the height of the {@link EnergyBar} from the {@link #capacity} {@link #energy}
	 * 
	 * @return the height of the energy bar
	 */
	private int getEnergyBarHeight() {
		return (int) ((this.capacity != 0 && this.energy != 0) ? (this.energy * this.height) / this.capacity : 0);
	}

	/**
	 * Update the energy inside of this {@link EnergyBar} from the {@link TileEntity} at the given {@link BlockPos}
	 * 
	 * @param pos
	 *            The position of the {@link TileEntity}
	 * @param side
	 *            The side of the {@link TileEntity} to get the energy from. For use with {@link Capability}s
	 */
	public void syncData(BlockPos pos, EnumFacing side) {
		EnergyUtils.syncEnergyData(EnergyUnits.REDSTONE_FLUX, pos, side, CJCore.MODID);
	}
}

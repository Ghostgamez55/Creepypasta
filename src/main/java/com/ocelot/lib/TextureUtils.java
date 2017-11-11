package com.ocelot.lib;

import java.awt.image.BufferedImage;

import com.ocelot.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

public class TextureUtils {

	private static Minecraft mc = Minecraft.getMinecraft();

	/**
	 * Creates a texture from a buffered image.
	 * 
	 * @param image
	 *            The image to become a texture
	 * @return The {@link ResourceLocation} to that texture
	 */
	public static ResourceLocation createBufferedImageTexture(BufferedImage image) {
		return mc.getTextureManager().getDynamicTextureLocation(" ", new DynamicTexture(image));
	}

	/**
	 * Deletes the specified image from memory.
	 * 
	 * @param image
	 *            The image to delete
	 */
	public static void deleteTexture(ResourceLocation image) {
		mc.getTextureManager().deleteTexture(image);
	}

	/**
	 * Binds the specified image.
	 * 
	 * @param image
	 *            The image to bind
	 */
	public static void bindTexture(ResourceLocation image) {
		mc.getTextureManager().bindTexture(image);
	}

	/**
	 * Binds a texture using the specified domain and path.
	 * 
	 * @param domain
	 *            The domain of the texture
	 * @param path
	 *            The path to the texture
	 */
	public static void bindTexture(String domain, String path) {
		bindTexture(new ResourceLocation(domain, path));
	}

	/**
	 * Binds a texture using the specified path.
	 * 
	 * @param path
	 *            The path to the texture
	 */
	public static void bindTexture(String path) {
		bindTexture(Reference.MOD_ID, path);
	}
}
package com.ocelot.api.utils;

import java.awt.image.BufferedImage;

import com.ocelot.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;

/**
 * <em><b>Copyright (c) 2017 Ocelot5836.</b></em>
 * 
 * <br>
 * </br>
 * 
 * This class holds methods to manage textures easily.
 * 
 * @author Ocelot5836
 */
public class TextureUtils {

	private static final Minecraft MC = Minecraft.getMinecraft();

	/**
	 * Creates a texture from a buffered image.
	 * 
	 * @param image
	 *            The image to become a texture
	 * @return The {@link ResourceLocation} to that texture
	 */
	public static ResourceLocation createBufferedImageTexture(BufferedImage image) {
		return MC.getTextureManager().getDynamicTextureLocation(" ", new DynamicTexture(image));
	}

	/**
	 * Deletes the specified texture from memory.
	 * 
	 * @param texture
	 *            The texture to delete
	 */
	public static void deleteTexture(ResourceLocation texture) {
		MC.getTextureManager().deleteTexture(texture);
	}

	/**
	 * Binds the specified texture.
	 * 
	 * @param texture
	 *            The texture to bind
	 */
	public static void bindTexture(ResourceLocation texture) {
		MC.getTextureManager().bindTexture(texture);
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

	/**
	 * @return The default missing image texture
	 */
	public static TextureAtlasSprite getMissingSprite() {
		return MC.getTextureMapBlocks().getMissingSprite();
	}
}
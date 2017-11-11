package cjminecraft.core.proxy;

import cjminecraft.core.config.CJCoreConfig;

/**
 * For all things client
 * 
 * @author CJMinecraft
 *
 */
public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() {
		super.preInit();
		CJCoreConfig.clientPreInit();
	}
}
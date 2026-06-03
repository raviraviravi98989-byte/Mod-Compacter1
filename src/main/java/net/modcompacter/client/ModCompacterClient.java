package net.modcompacter.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.modcompacter.ModCompacter;
import net.modcompacter.screen.CompacterScreen;

public class ModCompacterClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(ModCompacter.COMPACTER_SCREEN_HANDLER, CompacterScreen::new);
    }
}

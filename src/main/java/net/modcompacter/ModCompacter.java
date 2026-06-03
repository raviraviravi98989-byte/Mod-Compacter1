package net.modcompacter;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.modcompacter.block.CompacterBlock;
import net.modcompacter.block.CompacterBlockEntity;
import net.modcompacter.screen.CompacterScreenHandler;

public class ModCompacter implements ModInitializer {
    public static final String MODID = "modcompacter";

    public static final Block COMPACTER_BLOCK = new CompacterBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f));

    public static final Block COMPACTED_COBBLE = new Block(FabricBlockSettings.of(Material.STONE).strength(2.0f));

    public static final ScreenHandlerType<CompacterScreenHandler> COMPACTER_SCREEN_HANDLER =
            ScreenHandlerRegistry.registerSimple(new Identifier(MODID, "compacter"), CompacterScreenHandler::new);

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier(MODID, "compacter"), COMPACTER_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(MODID, "compacted_cobblestone"), COMPACTED_COBBLE);
    }
}

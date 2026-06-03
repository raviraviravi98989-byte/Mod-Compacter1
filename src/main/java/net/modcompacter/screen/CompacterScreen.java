package net.modcompacter.screen;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

public class CompacterScreen extends HandledScreen<net.modcompacter.screen.CompacterScreenHandler> {
    public CompacterScreen(net.modcompacter.screen.CompacterScreenHandler handler, PlayerInventory inv, Text title) {
        super(handler, inv, title);
    }

    @Override
    protected void init() {
        super.init();
        // Add compact button etc. Implementation left minimal.
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}

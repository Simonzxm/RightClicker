package io.github.simonzxm.config;

import io.github.simonzxm.RightClicker;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;

import javax.annotation.Nonnull;

public class rcConfigGui extends GuiConfig {

    public rcConfigGui(GuiScreen parentScreen) {
        super(parentScreen, new ConfigElement(RightClicker.instance.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), RightClicker.MODID, false, false, I18n.format(RightClicker.MODID + ".config.title"));
    }

    @Override
    public void initGui() {
        if (this.entryList == null || this.needsRefresh)
        {
            this.entryList = new GuiConfigEntries(this, mc) {
                @SuppressWarnings({ "unused", "null" })
                @Override
                protected void drawContainerBackground(@Nonnull Tessellator tessellator) {
                    if (mc.theWorld == null) {
                        super.drawContainerBackground(tessellator);
                    }
                }
            };
            this.needsRefresh = false;
        }
        super.initGui();
    }

    @Override
    public void drawDefaultBackground() {
        drawWorldBackground(0);
    }
}

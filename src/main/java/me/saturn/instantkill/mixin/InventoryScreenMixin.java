package me.saturn.instantkill.mixin;

import java.time.Instant;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.saturn.instantkill.InstantKill;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import java.awt.Color;

@Mixin(InventoryScreen.class)
public class InventoryScreenMixin extends Screen{

    protected InventoryScreenMixin(Text title){
        super(title);
    }

    @Inject(method = "init", at = @At("HEAD"))
    public void onInit(CallbackInfo ci){
        ButtonWidget toggle = ButtonWidget.builder(Text.of("InstantKill: " + (InstantKill.shouldAddVelocity ? "On" : "Off")), b -> {
            InstantKill.shouldAddVelocity = !InstantKill.shouldAddVelocity;
            if (InstantKill.mc.player != null) {
                InstantKill.mc.setScreen(new InventoryScreen(InstantKill.mc.player));
            }
        }).dimensions(1, 1, 100, 20).build();
        this.addDrawableChild(toggle);
    }

    @Inject(method = "render", at = @At("HEAD"))
    public void onRender(MatrixStack matrices, int a, int b, float d, CallbackInfo ci){
        int pp = this.textRenderer.getWidth("Made by Saturn5Vfive <3") / 2;
        drawCenteredTextWithShadow(matrices, textRenderer, Text.of("Made by Saturn5Vfive <3"), pp + 5, InstantKill.mc.getWindow().getScaledHeight() - 10, new Color(255, 255, 255, 255).getRGB());
    }
}
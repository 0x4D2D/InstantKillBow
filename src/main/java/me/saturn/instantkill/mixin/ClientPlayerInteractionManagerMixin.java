package me.saturn.instantkill.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.saturn.instantkill.InstantKill;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    @Inject(at = @At("HEAD"), method = "stopUsingItem")
    private void onStopUsingItem(PlayerEntity player, CallbackInfo ci){
        if(player.getInventory().getMainHandStack().getItem().equals(Items.BOW)){
            InstantKill.addVelocityToPlayer();
        }
    }
}

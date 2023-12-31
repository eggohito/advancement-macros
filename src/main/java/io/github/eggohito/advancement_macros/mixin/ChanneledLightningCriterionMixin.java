package io.github.eggohito.advancement_macros.mixin;

import io.github.eggohito.advancement_macros.access.MacroContext;
import io.github.eggohito.advancement_macros.macro.ChanneledLightningCriterionMacro;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.ChanneledLightningCriterion;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collection;

@Mixin(ChanneledLightningCriterion.class)
public abstract class ChanneledLightningCriterionMixin extends AbstractCriterion<ChanneledLightningCriterion.Conditions> {

    @Inject(method = "trigger", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/criterion/ChanneledLightningCriterion;trigger(Lnet/minecraft/server/network/ServerPlayerEntity;Ljava/util/function/Predicate;)V"))
    private void advancement_macros$passContext(ServerPlayerEntity player, Collection<? extends Entity> victims, CallbackInfo ci) {
        ((MacroContext) this).advancement_macros$setContext(this, triggerContext -> triggerContext
            .add(ChanneledLightningCriterionMacro.VICTIMS_KEY, victims));
    }

}

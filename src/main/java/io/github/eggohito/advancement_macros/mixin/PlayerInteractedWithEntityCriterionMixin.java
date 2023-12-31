package io.github.eggohito.advancement_macros.mixin;

import io.github.eggohito.advancement_macros.access.MacroContext;
import io.github.eggohito.advancement_macros.macro.PlayerInteractedWithEntityCriterionMacro;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.PlayerInteractedWithEntityCriterion;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerInteractedWithEntityCriterion.class)
public abstract class PlayerInteractedWithEntityCriterionMixin extends AbstractCriterion<PlayerInteractedWithEntityCriterion.Conditions> {

    @Inject(method = "trigger", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/criterion/PlayerInteractedWithEntityCriterion;trigger(Lnet/minecraft/server/network/ServerPlayerEntity;Ljava/util/function/Predicate;)V"))
    private void advancement_macros$passContext(ServerPlayerEntity player, ItemStack stack, Entity entity, CallbackInfo ci) {
        ((MacroContext) this).advancement_macros$setContext(this, triggerContext -> triggerContext
            .add(PlayerInteractedWithEntityCriterionMacro.USED_ITEM_KEY_FIELD, stack)
            .add(PlayerInteractedWithEntityCriterionMacro.INTERACTED_ENTITY_KEY_FIELD, entity));
    }

}

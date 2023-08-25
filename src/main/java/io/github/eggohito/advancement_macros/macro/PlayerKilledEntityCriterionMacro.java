package io.github.eggohito.advancement_macros.macro;

import com.mojang.serialization.Codec;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

public class PlayerKilledEntityCriterionMacro extends OnKilledCriterionMacro {

    public static final Codec<OnKilledCriterionMacro> CODEC = getCodec(PlayerKilledEntityCriterionMacro::new);

    public PlayerKilledEntityCriterionMacro(String killerKey, String victimKey, String killingBlowKey) {
        super(Criteria.PLAYER_KILLED_ENTITY.getId(), killerKey, victimKey, killingBlowKey);
    }

    @Override
    public Type getType() {
        return () -> CODEC;
    }

    @Override
    public void writeToNbt(NbtCompound rootNbt, Object object) {

        super.writeToNbt(rootNbt, object);

        if (object instanceof ServerPlayerEntity player) {
             rootNbt.putString(getKillerKey(), player.getUuidAsString());
        } else if (object instanceof Entity entity) {
            rootNbt.putString(getVictimKey(), entity.getUuidAsString());
        }

    }

    public static Pair<Identifier, Type> getFactory() {
        return new Pair<>(Criteria.PLAYER_KILLED_ENTITY.getId(), () -> CODEC);
    }

}
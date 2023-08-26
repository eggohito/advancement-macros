package io.github.eggohito.advancement_macros.macro;

import com.mojang.serialization.Codec;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

public class RideEntityInLavaCriterionMacro extends TravelCriterionMacro {

    public static final Codec<TravelCriterionMacro> CODEC = getCodec(RideEntityInLavaCriterionMacro::new);

    public RideEntityInLavaCriterionMacro(String startLocationKey) {
        super(Criteria.RIDE_ENTITY_IN_LAVA.getId(), startLocationKey);
    }

    @Override
    public Type getType() {
        return () -> CODEC;
    }

    public static Pair<Identifier, Type> getFactory() {
        return new Pair<>(Criteria.RIDE_ENTITY_IN_LAVA.getId(), () -> CODEC);
    }

}
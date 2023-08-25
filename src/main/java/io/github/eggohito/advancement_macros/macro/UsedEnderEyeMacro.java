package io.github.eggohito.advancement_macros.macro;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.eggohito.advancement_macros.util.NbtUtil;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;

public class UsedEnderEyeMacro extends Macro {

    public static final Codec<UsedEnderEyeMacro> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        Codec.STRING.optionalFieldOf("stronghold_location_key", "stronghold_location").forGetter(UsedEnderEyeMacro::getStrongholdPosKey)
    ).apply(instance, UsedEnderEyeMacro::new));

    private final String strongholdPosKey;

    public UsedEnderEyeMacro(String strongholdPosKey) {
        super(Criteria.USED_ENDER_EYE.getId());
        this.strongholdPosKey = strongholdPosKey;
    }

    public String getStrongholdPosKey() {
        return strongholdPosKey;
    }

    @Override
    public Type getType() {
        return () -> CODEC;
    }

    @Override
    public void writeToNbt(NbtCompound rootNbt, Object object) {
        if (object instanceof BlockPos blockPos) {
            NbtUtil.writeBlockPosToNbt(rootNbt, strongholdPosKey, blockPos);
        }
    }

    public static Pair<Identifier, Type> getFactory() {
        return new Pair<>(Criteria.USED_ENDER_EYE.getId(), () -> CODEC);
    }

}
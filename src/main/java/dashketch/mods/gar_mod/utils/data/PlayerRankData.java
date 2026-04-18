package dashketch.mods.gar_mod.utils.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class PlayerRankData {
    public final int rank;
    public final int points;
    public final int tickCounter;

    public static int getRankForPoints(int points) {
        if (points >= 210) return 7;
        if (points >= 150) return 6;
        if (points >= 100) return 5;
        if (points >= 40) return 4;
        if (points >= 15) return 3;
        if (points >= 2) return 2;
        return 1;
    }

    // This Codec translates Java variables into a format Minecraft can save to the hard drive
    public static final Codec<PlayerRankData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("rank").forGetter(d -> d.rank),
            Codec.INT.fieldOf("points").forGetter(d -> d.points),
            Codec.INT.fieldOf("tickCounter").forGetter(d -> d.tickCounter)
    ).apply(instance, PlayerRankData::new));

    public PlayerRankData(int rank, int points, int tickCounter) {
        this.rank = rank;
        this.points = points;
        this.tickCounter = tickCounter;
    }
}
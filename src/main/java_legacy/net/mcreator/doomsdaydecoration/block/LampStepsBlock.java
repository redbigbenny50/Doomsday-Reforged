package net.mcreator.doomsdaydecoration.block;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class LampStepsBlock extends SlabBlock {
   public LampStepsBlock() {
      super(
         Properties.of()
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.STONE)
            .strength(1.0F, 10.0F)
            .lightLevel(s -> 15)
            .requiresCorrectToolForDrops()
            .canOcclude((bs, br, bp) -> true)
            .isSolid((bs, br, bp) -> true)
            .isAir()
      );
   }
}

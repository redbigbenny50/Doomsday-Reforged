package net.mcreator.doomsdaydecoration.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class GreenscreenstaircaseBlock extends StairBlock {
   public GreenscreenstaircaseBlock() {
      super(
         () -> Blocks.EMPTY.defaultBlockState(),
         Properties.of()
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.STONE)
            .strength(1.0F, 10.0F)
            .lightLevel(s -> 15)
            .requiresCorrectToolForDrops()
            .isAir()
      );
   }

   public float isRainingAt() {
      return 10.0F;
   }

   public boolean addParticle(BlockState state) {
      return false;
   }
}

package net.mcreator.doomsdaydecoration.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class Door7Block extends DoorBlock {
   public Door7Block() {
      super(
         Properties.of()
            .intersects()
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.WOOD)
            .strength(1.0F, 10.0F)
            .requiresCorrectToolForDrops()
            .noOcclusion()
            .isValidSpawn((bs, br, bp) -> false)
            .isAir(),
         BlockSetType.WEST
      );
   }

   public int getLightEmission(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return 0;
   }
}

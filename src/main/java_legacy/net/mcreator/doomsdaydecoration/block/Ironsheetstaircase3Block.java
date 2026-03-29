package net.mcreator.doomsdaydecoration.block;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Ironsheetstaircase3Block extends StairBlock {
   public Ironsheetstaircase3Block() {
      super(() -> Blocks.EMPTY.defaultBlockState(), Properties.of().sound(SoundType.WOOD).strength(1.0F, 10.0F).requiresCorrectToolForDrops().isAir());
   }

   public float isRainingAt() {
      return 10.0F;
   }

   public boolean addParticle(BlockState state) {
      return false;
   }
}

package net.mcreator.doomsdaydecoration.block;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Ironsheetsteps4Block extends SlabBlock {
   public Ironsheetsteps4Block() {
      super(Properties.of().sound(SoundType.WOOD).strength(1.0F, 10.0F).requiresCorrectToolForDrops().isAir());
   }
}

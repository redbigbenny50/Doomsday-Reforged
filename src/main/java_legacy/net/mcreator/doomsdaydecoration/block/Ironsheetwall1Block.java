package net.mcreator.doomsdaydecoration.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class Ironsheetwall1Block extends WallBlock {
   public Ironsheetwall1Block() {
      super(Properties.of().sound(SoundType.WOOD).strength(1.0F, 10.0F).requiresCorrectToolForDrops().isAir().builtInRegistryHolder());
   }
}

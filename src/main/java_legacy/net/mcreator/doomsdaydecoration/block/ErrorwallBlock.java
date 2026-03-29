package net.mcreator.doomsdaydecoration.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class ErrorwallBlock extends WallBlock {
   public ErrorwallBlock() {
      super(
         Properties.of()
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.STONE)
            .strength(-1.0F, 3600000.0F)
            .requiresCorrectToolForDrops()
            .isAir()
            .builtInRegistryHolder()
      );
   }
}

package net.mcreator.doomsdaydecoration.block;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class PooltileswallBlock extends WallBlock {
   public PooltileswallBlock() {
      super(Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.METAL).strength(1.0F, 10.0F).requiresCorrectToolForDrops().isAir().builtInRegistryHolder());
   }
}

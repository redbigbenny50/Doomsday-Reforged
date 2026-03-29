package net.mcreator.doomsdaydecoration.block;

import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class Nomapfence2Block extends FenceBlock {
   public Nomapfence2Block() {
      super(Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE).strength(1.0F, 10.0F).requiresCorrectToolForDrops().isAir().builtInRegistryHolder());
   }
}

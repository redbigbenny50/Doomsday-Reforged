package net.mcreator.doomsdaydecoration.block;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class Bluepooltilessteps2Block extends SlabBlock {
   public Bluepooltilessteps2Block() {
      super(Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.METAL).strength(1.0F, 10.0F).requiresCorrectToolForDrops().isAir());
   }
}

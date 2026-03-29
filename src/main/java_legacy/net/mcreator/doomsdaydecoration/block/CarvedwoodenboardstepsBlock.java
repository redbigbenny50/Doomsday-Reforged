package net.mcreator.doomsdaydecoration.block;

import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class CarvedwoodenboardstepsBlock extends SlabBlock {
   public CarvedwoodenboardstepsBlock() {
      super(Properties.of().intersects().instrument(NoteBlockInstrument.BASS).sound(SoundType.BAMBOO).strength(1.0F, 10.0F).requiresCorrectToolForDrops().isAir());
   }
}

package net.mcreator.doomsdaydecoration.block;

import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class ErrorbuttonBlock extends ButtonBlock {
   public ErrorbuttonBlock() {
      super(
         Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE).strength(-1.0F, 3600000.0F).requiresCorrectToolForDrops().isAir(),
         BlockSetType.UP,
         20,
         false
      );
   }
}

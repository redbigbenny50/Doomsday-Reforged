package net.mcreator.doomsdaydecoration.block;

import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class ErrorPaneBlock extends IronBarsBlock {
   public ErrorPaneBlock() {
      super(Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE).strength(-1.0F, 3600000.0F).requiresCorrectToolForDrops());
   }
}

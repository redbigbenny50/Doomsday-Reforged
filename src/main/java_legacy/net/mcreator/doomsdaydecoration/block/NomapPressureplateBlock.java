package net.mcreator.doomsdaydecoration.block;

import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.PressurePlateBlock.Sensitivity;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class NomapPressureplateBlock extends PressurePlateBlock {
   public NomapPressureplateBlock() {
      super(
         Sensitivity.MOBS,
         Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.STONE).strength(1.0F, 10.0F).requiresCorrectToolForDrops().isAir().builtInRegistryHolder(),
         BlockSetType.EAST
      );
   }
}

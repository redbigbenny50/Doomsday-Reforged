package net.mcreator.doomsdaydecoration.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class BluepooltilesBlock extends Block {
   public BluepooltilesBlock() {
      super(Properties.of().instrument(NoteBlockInstrument.BASEDRUM).sound(SoundType.METAL).strength(1.0F, 10.0F).requiresCorrectToolForDrops());
   }

   public int getLightEmission(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return 15;
   }
}

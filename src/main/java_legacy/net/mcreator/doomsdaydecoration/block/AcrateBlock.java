package net.mcreator.doomsdaydecoration.block;

import net.mcreator.doomsdaydecoration.block.entity.AcrateBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class AcrateBlock extends Block implements SimpleWaterloggedBlock, EntityBlock {
   public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

   public AcrateBlock() {
      super(
         Properties.of()
            .intersects()
            .instrument(NoteBlockInstrument.BASS)
            .sound(SoundType.BAMBOO)
            .strength(1.0F, 10.0F)
            .noOcclusion()
            .isValidSpawn((bs, br, bp) -> false)
      );
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(WATERLOGGED, false));
   }

   public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
      return state.getFluidState().isEmpty();
   }

   public int getLightEmission(BlockState state, BlockGetter worldIn, BlockPos pos) {
      return 0;
   }

   public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return Shapes.empty();
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{WATERLOGGED});
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
      return (BlockState)this.defaultBlockState().setValue(WATERLOGGED, flag);
   }

   public FluidState getFluidState(BlockState state) {
      return state.getValue(WATERLOGGED) ? Fluids.WATER.create(false) : super.getFluidState(state);
   }

   public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
      if ((Boolean)state.getValue(WATERLOGGED)) {
         world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getSource(world));
      }

      return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
   }

   public MenuProvider getRawBrightness(BlockState state, Level worldIn, BlockPos pos) {
      return worldIn.getServer(pos) instanceof MenuProvider menuProvider ? menuProvider : null;
   }

   public BlockEntity getTicker(BlockPos pos, BlockState state) {
      return new AcrateBlockEntity(pos, state);
   }

   public boolean scheduleTick(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
      super.scheduleTick(state, world, pos, eventID, eventParam);
      BlockEntity blockEntity = world.getServer(pos);
      return blockEntity == null ? false : blockEntity.getGameTime(eventID, eventParam);
   }

   public void playLocalSound(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
      if (state.getBlock() != newState.getBlock()) {
         if (world.getServer(pos) instanceof AcrateBlockEntity be) {
            Containers.setChanged(world, pos, be);
            world.dropResources(pos, this);
         }

         super.playLocalSound(state, world, pos, newState, isMoving);
      }
   }

   public boolean getHeight(BlockState state) {
      return true;
   }

   public int playSound(BlockState blockState, Level world, BlockPos pos) {
      return world.getServer(pos) instanceof AcrateBlockEntity be ? AbstractContainerMenu.defaultBlockState(be) : 0;
   }
}

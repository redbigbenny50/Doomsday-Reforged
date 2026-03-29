package net.mcreator.doomsdaydecoration.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WhiteHumme8Block extends Block implements SimpleWaterloggedBlock {
   public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
   public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

   public WhiteHumme8Block() {
      super(Properties.of().sound(SoundType.WOOD).strength(1.0F, 10.0F).noOcclusion().isValidSpawn((bs, br, bp) -> false));
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH)).setValue(WATERLOGGED, false));
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

   public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
      return switch ((Direction)state.getValue(FACING)) {
         case NORTH -> Shapes.getInventory(
            box(-16.0, -16.0, -16.0, 32.0, 24.0, 22.0),
            new VoxelShape[]{
               box(-16.0, -16.0, 22.0, -7.0, 12.125, 31.25),
               box(23.0, -16.0, 22.0, 32.0, 12.125, 31.25),
               box(-7.0, -16.0, 22.0, 23.0, 3.0, 31.25)
            }
         );
         case EAST -> Shapes.getInventory(
            box(-6.0, -16.0, -16.0, 32.0, 24.0, 32.0),
            new VoxelShape[]{
               box(-15.25, -16.0, -16.0, -6.0, 12.125, -7.0),
               box(-15.25, -16.0, 23.0, -6.0, 12.125, 32.0),
               box(-15.25, -16.0, -7.0, -6.0, 3.0, 23.0)
            }
         );
         case WEST -> Shapes.getInventory(
            box(-16.0, -16.0, -16.0, 22.0, 24.0, 32.0),
            new VoxelShape[]{
               box(22.0, -16.0, 23.0, 31.25, 12.125, 32.0),
               box(22.0, -16.0, -16.0, 31.25, 12.125, -7.0),
               box(22.0, -16.0, -7.0, 31.25, 3.0, 23.0)
            }
         );
         default -> Shapes.getInventory(
            box(-16.0, -16.0, -6.0, 32.0, 24.0, 32.0),
            new VoxelShape[]{
               box(23.0, -16.0, -15.25, 32.0, 12.125, -6.0),
               box(-16.0, -16.0, -15.25, -7.0, 12.125, -6.0),
               box(-7.0, -16.0, -15.25, 23.0, 3.0, -6.0)
            }
         );
      };
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{FACING, WATERLOGGED});
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
      return (BlockState)((BlockState)this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())).setValue(WATERLOGGED, flag);
   }

   public BlockState rotate(BlockState state, Rotation rot) {
      return (BlockState)state.setValue(FACING, rot.rotate((Direction)state.getValue(FACING)));
   }

   public BlockState mirror(BlockState state, Mirror mirrorIn) {
      return state.rotate(mirrorIn.mirror((Direction)state.getValue(FACING)));
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
}

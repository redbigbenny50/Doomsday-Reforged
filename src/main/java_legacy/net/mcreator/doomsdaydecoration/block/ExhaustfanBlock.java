package net.mcreator.doomsdaydecoration.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ExhaustfanBlock extends Block implements SimpleWaterloggedBlock {
   public static final EnumProperty<Axis> AXIS = BlockStateProperties.LIT;
   public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

   public ExhaustfanBlock() {
      super(Properties.of().sound(SoundType.WOOD).strength(1.0F, 10.0F).noOcclusion().isValidSpawn((bs, br, bp) -> false));
      this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(AXIS, Axis.Y)).setValue(WATERLOGGED, false));
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
      return switch ((Axis)state.getValue(AXIS)) {
         case X -> Shapes.getInventory(
            box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0),
            new VoxelShape[]{
               box(0.0, 1.0, 0.0, 16.0, 15.0, 1.0),
               box(0.0, 15.0, 0.0, 16.0, 16.0, 16.0),
               box(0.0, 1.0, 15.0, 16.0, 15.0, 16.0),
               box(6.0, 1.0, 1.0, 10.0, 15.0, 15.0)
            }
         );
         case Y -> Shapes.getInventory(
            box(0.0, 0.0, 0.0, 16.0, 16.0, 1.0),
            new VoxelShape[]{
               box(0.0, 0.0, 1.0, 1.0, 16.0, 15.0),
               box(0.0, 0.0, 15.0, 16.0, 16.0, 16.0),
               box(15.0, 0.0, 1.0, 16.0, 16.0, 15.0),
               box(1.0, 6.0, 1.0, 15.0, 10.0, 15.0)
            }
         );
         case Z -> Shapes.getInventory(
            box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0),
            new VoxelShape[]{
               box(0.0, 1.0, 0.0, 1.0, 15.0, 16.0),
               box(0.0, 15.0, 0.0, 16.0, 16.0, 16.0),
               box(15.0, 1.0, 0.0, 16.0, 15.0, 16.0),
               box(1.0, 1.0, 6.0, 15.0, 15.0, 10.0)
            }
         );
         default -> throw new IncompatibleClassChangeError();
      };
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{AXIS, WATERLOGGED});
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
      return (BlockState)((BlockState)this.defaultBlockState().setValue(AXIS, context.updateOrDestroy().getOpposite())).setValue(WATERLOGGED, flag);
   }

   public BlockState rotate(BlockState state, Rotation rot) {
      if (rot == Rotation.CLOCKWISE_90 || rot == Rotation.COUNTERCLOCKWISE_90) {
         if (state.getValue(AXIS) == Axis.X) {
            return (BlockState)state.setValue(AXIS, Axis.Z);
         }

         if (state.getValue(AXIS) == Axis.Z) {
            return (BlockState)state.setValue(AXIS, Axis.X);
         }
      }

      return state;
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

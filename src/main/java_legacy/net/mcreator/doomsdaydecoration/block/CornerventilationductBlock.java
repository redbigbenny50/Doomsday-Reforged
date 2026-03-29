package net.mcreator.doomsdaydecoration.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CornerventilationductBlock extends Block implements SimpleWaterloggedBlock {
   public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
   public static final EnumProperty<AttachFace> FACE = FaceAttachedHorizontalDirectionalBlock.BLOCK_ENTITY_TICKER;
   public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

   public CornerventilationductBlock() {
      super(Properties.of().sound(SoundType.WOOD).strength(1.0F, 10.0F).noOcclusion().isValidSpawn((bs, br, bp) -> false));
      this.registerDefaultState(
         (BlockState)((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH)).setValue(FACE, AttachFace.WALL))
            .setValue(WATERLOGGED, false)
      );
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
         case NORTH -> {
            switch ((AttachFace)state.getValue(FACE)) {
               case FLOOR:
                  yield Shapes.getInventory(
                     box(1.0, 0.0, 0.0, 15.0, 16.0, 1.0),
                     new VoxelShape[]{
                        box(1.0, 15.0, 1.0, 15.0, 16.0, 16.0),
                        box(0.0, 0.0, 0.0, 1.0, 16.0, 16.0),
                        box(1.0, 0.0, 15.0, 15.0, 1.0, 16.0),
                        box(15.0, 0.0, 0.0, 16.0, 16.0, 16.0)
                     }
                  );
               case WALL:
                  yield Shapes.getInventory(
                     box(1.0, 0.0, 0.0, 15.0, 1.0, 16.0),
                     new VoxelShape[]{
                        box(1.0, 1.0, 0.0, 15.0, 16.0, 1.0),
                        box(0.0, 0.0, 0.0, 1.0, 16.0, 16.0),
                        box(1.0, 15.0, 15.0, 15.0, 16.0, 16.0),
                        box(15.0, 0.0, 0.0, 16.0, 16.0, 16.0)
                     }
                  );
               case CEILING:
                  yield Shapes.getInventory(
                     box(1.0, 0.0, 0.0, 15.0, 16.0, 1.0),
                     new VoxelShape[]{
                        box(1.0, 0.0, 1.0, 15.0, 1.0, 16.0),
                        box(15.0, 0.0, 0.0, 16.0, 16.0, 16.0),
                        box(1.0, 15.0, 15.0, 15.0, 16.0, 16.0),
                        box(0.0, 0.0, 0.0, 1.0, 16.0, 16.0)
                     }
                  );
               default:
                  throw new IncompatibleClassChangeError();
            }
         }
         case EAST -> {
            switch ((AttachFace)state.getValue(FACE)) {
               case FLOOR:
                  yield Shapes.getInventory(
                     box(15.0, 0.0, 1.0, 16.0, 16.0, 15.0),
                     new VoxelShape[]{
                        box(0.0, 15.0, 1.0, 15.0, 16.0, 15.0),
                        box(0.0, 0.0, 0.0, 16.0, 16.0, 1.0),
                        box(0.0, 0.0, 1.0, 1.0, 1.0, 15.0),
                        box(0.0, 0.0, 15.0, 16.0, 16.0, 16.0)
                     }
                  );
               case WALL:
                  yield Shapes.getInventory(
                     box(0.0, 0.0, 1.0, 16.0, 1.0, 15.0),
                     new VoxelShape[]{
                        box(15.0, 1.0, 1.0, 16.0, 16.0, 15.0),
                        box(0.0, 0.0, 0.0, 16.0, 16.0, 1.0),
                        box(0.0, 15.0, 1.0, 1.0, 16.0, 15.0),
                        box(0.0, 0.0, 15.0, 16.0, 16.0, 16.0)
                     }
                  );
               case CEILING:
                  yield Shapes.getInventory(
                     box(15.0, 0.0, 1.0, 16.0, 16.0, 15.0),
                     new VoxelShape[]{
                        box(0.0, 0.0, 1.0, 15.0, 1.0, 15.0),
                        box(0.0, 0.0, 15.0, 16.0, 16.0, 16.0),
                        box(0.0, 15.0, 1.0, 1.0, 16.0, 15.0),
                        box(0.0, 0.0, 0.0, 16.0, 16.0, 1.0)
                     }
                  );
               default:
                  throw new IncompatibleClassChangeError();
            }
         }
         case WEST -> {
            switch ((AttachFace)state.getValue(FACE)) {
               case FLOOR:
                  yield Shapes.getInventory(
                     box(0.0, 0.0, 1.0, 1.0, 16.0, 15.0),
                     new VoxelShape[]{
                        box(1.0, 15.0, 1.0, 16.0, 16.0, 15.0),
                        box(0.0, 0.0, 15.0, 16.0, 16.0, 16.0),
                        box(15.0, 0.0, 1.0, 16.0, 1.0, 15.0),
                        box(0.0, 0.0, 0.0, 16.0, 16.0, 1.0)
                     }
                  );
               case WALL:
                  yield Shapes.getInventory(
                     box(0.0, 0.0, 1.0, 16.0, 1.0, 15.0),
                     new VoxelShape[]{
                        box(0.0, 1.0, 1.0, 1.0, 16.0, 15.0),
                        box(0.0, 0.0, 15.0, 16.0, 16.0, 16.0),
                        box(15.0, 15.0, 1.0, 16.0, 16.0, 15.0),
                        box(0.0, 0.0, 0.0, 16.0, 16.0, 1.0)
                     }
                  );
               case CEILING:
                  yield Shapes.getInventory(
                     box(0.0, 0.0, 1.0, 1.0, 16.0, 15.0),
                     new VoxelShape[]{
                        box(1.0, 0.0, 1.0, 16.0, 1.0, 15.0),
                        box(0.0, 0.0, 0.0, 16.0, 16.0, 1.0),
                        box(15.0, 15.0, 1.0, 16.0, 16.0, 15.0),
                        box(0.0, 0.0, 15.0, 16.0, 16.0, 16.0)
                     }
                  );
               default:
                  throw new IncompatibleClassChangeError();
            }
         }
         default -> {
            switch ((AttachFace)state.getValue(FACE)) {
               case FLOOR:
                  yield Shapes.getInventory(
                     box(1.0, 0.0, 15.0, 15.0, 16.0, 16.0),
                     new VoxelShape[]{
                        box(1.0, 15.0, 0.0, 15.0, 16.0, 15.0),
                        box(15.0, 0.0, 0.0, 16.0, 16.0, 16.0),
                        box(1.0, 0.0, 0.0, 15.0, 1.0, 1.0),
                        box(0.0, 0.0, 0.0, 1.0, 16.0, 16.0)
                     }
                  );
                  break;
               case WALL:
                  yield Shapes.getInventory(
                     box(1.0, 0.0, 0.0, 15.0, 1.0, 16.0),
                     new VoxelShape[]{
                        box(1.0, 1.0, 15.0, 15.0, 16.0, 16.0),
                        box(15.0, 0.0, 0.0, 16.0, 16.0, 16.0),
                        box(1.0, 15.0, 0.0, 15.0, 16.0, 1.0),
                        box(0.0, 0.0, 0.0, 1.0, 16.0, 16.0)
                     }
                  );
                  break;
               case CEILING:
                  yield Shapes.getInventory(
                     box(1.0, 0.0, 15.0, 15.0, 16.0, 16.0),
                     new VoxelShape[]{
                        box(1.0, 0.0, 0.0, 15.0, 1.0, 15.0),
                        box(0.0, 0.0, 0.0, 1.0, 16.0, 16.0),
                        box(1.0, 15.0, 0.0, 15.0, 16.0, 1.0),
                        box(15.0, 0.0, 0.0, 16.0, 16.0, 16.0)
                     }
                  );
                  break;
               default:
                  throw new IncompatibleClassChangeError();
            }
         }
      };
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{FACING, FACE, WATERLOGGED});
   }

   public BlockState getStateForPlacement(BlockPlaceContext context) {
      boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
      return context.updateOrDestroy().getOpposite() == Axis.Y
         ? (BlockState)((BlockState)((BlockState)this.defaultBlockState()
                  .setValue(FACE, context.updateOrDestroy().getOpposite() == Direction.UP ? AttachFace.CEILING : AttachFace.FLOOR))
               .setValue(FACING, context.getHorizontalDirection()))
            .setValue(WATERLOGGED, flag)
         : (BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(FACE, AttachFace.WALL)).setValue(FACING, context.updateOrDestroy()))
            .setValue(WATERLOGGED, flag);
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

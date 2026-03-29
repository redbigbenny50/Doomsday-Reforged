package net.mcreator.doomsdaydecoration.block;

import net.mcreator.doomsdaydecoration.block.entity.AcrateBlockEntity;
import net.minecraft.core.BlockPos;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.Containers;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.phys.BlockHitResult;

public class AcrateBlock extends Block implements EntityBlock {
  public static final MapCodec<AcrateBlock> CODEC = simpleCodec(AcrateBlock::new);

  public AcrateBlock(BlockBehaviour.Properties properties) {
    super(properties);
  }

  @Override
  protected MapCodec<? extends Block> codec() {
    return CODEC;
  }

  @Override
  public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
    return new AcrateBlockEntity(pos, state);
  }

  @Override
  public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
    BlockEntity blockEntity = level.getBlockEntity(pos);
    return blockEntity instanceof MenuProvider provider ? provider : null;
  }

  @Override
  protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
    if (!level.isClientSide()) {
      MenuProvider provider = getMenuProvider(state, level, pos);
      if (provider != null) {
        player.openMenu(provider);
      }
    }
    return InteractionResult.sidedSuccess(level.isClientSide());
  }

  @Override
  protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
    if (!level.isClientSide()) {
      MenuProvider provider = getMenuProvider(state, level, pos);
      if (provider != null) {
        player.openMenu(provider);
      }
    }
    return ItemInteractionResult.sidedSuccess(level.isClientSide());
  }

  @Override
  public boolean triggerEvent(BlockState state, Level level, BlockPos pos, int id, int param) {
    super.triggerEvent(state, level, pos, id, param);
    BlockEntity blockEntity = level.getBlockEntity(pos);
    return blockEntity != null && blockEntity.triggerEvent(id, param);
  }

  @Override
  public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
    if (state.getBlock() != newState.getBlock()) {
      BlockEntity blockEntity = level.getBlockEntity(pos);
      if (blockEntity instanceof AcrateBlockEntity crate) {
        Containers.dropContents(level, pos, crate);
        level.updateNeighbourForOutputSignal(pos, this);
      }
    }
    super.onRemove(state, level, pos, newState, isMoving);
  }

  @Override
  public boolean hasAnalogOutputSignal(BlockState state) {
    return true;
  }

  @Override
  public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
    BlockEntity blockEntity = level.getBlockEntity(pos);
    return blockEntity instanceof AcrateBlockEntity crate ? AbstractContainerMenu.getRedstoneSignalFromContainer(crate) : 0;
  }
}

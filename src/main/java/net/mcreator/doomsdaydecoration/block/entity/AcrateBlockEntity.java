package net.mcreator.doomsdaydecoration.block.entity;

import java.util.stream.IntStream;
import javax.annotation.Nullable;
import net.mcreator.doomsdaydecoration.init.DoomsdayDecorationModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AcrateBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
  private NonNullList<ItemStack> items = NonNullList.withSize(9, ItemStack.EMPTY);

  public AcrateBlockEntity(BlockPos position, BlockState state) {
    super(DoomsdayDecorationModBlockEntities.ACRATE.get(), position, state);
  }

  @Override
  protected Component getDefaultName() {
    return Component.literal("acrate");
  }

  @Override
  protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
    return new ChestMenu(MenuType.GENERIC_9x1, id, inventory, this, 1);
  }

  @Override
  public int getContainerSize() {
    return items.size();
  }

  @Override
  public boolean isEmpty() {
    for (ItemStack stack : items) {
      if (!stack.isEmpty()) {
        return false;
      }
    }
    return true;
  }

  @Override
  protected NonNullList<ItemStack> getItems() {
    return items;
  }

  @Override
  protected void setItems(NonNullList<ItemStack> items) {
    this.items = items;
  }

  @Override
  protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
    super.loadAdditional(tag, registries);
    if (!tryLoadLootTable(tag)) {
      items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
    }
    ContainerHelper.loadAllItems(tag, items, registries);
  }

  @Override
  protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
    super.saveAdditional(tag, registries);
    if (!trySaveLootTable(tag)) {
      ContainerHelper.saveAllItems(tag, items, registries);
    }
  }

  @Override
  public ClientboundBlockEntityDataPacket getUpdatePacket() {
    return ClientboundBlockEntityDataPacket.create(this);
  }

  @Override
  public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
    return saveWithoutMetadata(registries);
  }

  @Override
  public int[] getSlotsForFace(Direction side) {
    return IntStream.range(0, getContainerSize()).toArray();
  }

  @Override
  public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
    return canPlaceItem(index, stack);
  }

  @Override
  public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
    return true;
  }
}

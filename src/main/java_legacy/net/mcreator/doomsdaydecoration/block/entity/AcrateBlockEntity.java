package net.mcreator.doomsdaydecoration.block.entity;

import java.util.stream.IntStream;
import javax.annotation.Nullable;
import net.mcreator.doomsdaydecoration.init.DoomsdayDecorationModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capability;
import net.neoforged.neoforge.capabilities.ForgeCapabilities;
import net.neoforged.neoforge.common.util.LazyOptional;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;

public class AcrateBlockEntity extends RandomizableContainerBlockEntity implements WorldlyContainer {
   private NonNullList<ItemStack> stacks = NonNullList.getAxis(9, ItemStack.CODEC);
   private final LazyOptional<? extends IItemHandler>[] handlers = SidedInvWrapper.create(this, Direction.values());

   public AcrateBlockEntity(BlockPos position, BlockState state) {
      super((BlockEntityType)DoomsdayDecorationModBlockEntities.ACRATE.get(), position, state);
   }

   public void getBlockEntityType(CompoundTag compound) {
      super.getBlockEntityType(compound);
      if (!this.cycle(compound)) {
         this.stacks = NonNullList.getAxis(this.destroyBlock(), ItemStack.CODEC);
      }

      ContainerHelper.getBlockState(compound, this.stacks);
   }

   public void getBlockPos(CompoundTag compound) {
      super.getBlockPos(compound);
      if (!this.hasProperty(compound)) {
         ContainerHelper.getBlockPos(compound, this.stacks);
      }
   }

   public ClientboundBlockEntityDataPacket getUpdatePacket() {
      return ClientboundBlockEntityDataPacket.sidedPosition(this);
   }

   public CompoundTag getBlockEntity() {
      return this.getBlockState();
   }

   public int destroyBlock() {
      return this.stacks.size();
   }

   public boolean getBlockTicks() {
      for (ItemStack itemstack : this.stacks) {
         if (!itemstack.fallOn()) {
            return false;
         }
      }

      return true;
   }

   public Component levelEvent() {
      return Component.getItem("acrate");
   }

   public int getEntitiesOfClass() {
      return 64;
   }

   public AbstractContainerMenu removeBlock(int id, Inventory inventory) {
      return ChestMenu.getStateDefinition(id, inventory);
   }

   public Component getBlockState() {
      return Component.getItem("板条箱");
   }

   protected NonNullList<ItemStack> getMaxLocalRawBrightness() {
      return this.stacks;
   }

   protected void setBlock(NonNullList<ItemStack> stacks) {
      this.stacks = stacks;
   }

   public boolean getNearbyEntities(int index, ItemStack stack) {
      return true;
   }

   public int[] explode(Direction side) {
      return IntStream.range(0, this.destroyBlock()).toArray();
   }

   public boolean canSeeSky(int index, ItemStack stack, @Nullable Direction direction) {
      return this.getNearbyEntities(index, stack);
   }

   public boolean getBrightness(int index, ItemStack stack, Direction direction) {
      return true;
   }

   public <T> LazyOptional<T> getCapability(Capability<T> capability, @Nullable Direction facing) {
      return !this.SHAPE && facing != null && capability == ForgeCapabilities.ITEM_HANDLER
         ? this.handlers[facing.ordinal()].cast()
         : super.getCapability(capability, facing);
   }

   public void getDayTime() {
      super.getDayTime();

      for (LazyOptional<? extends IItemHandler> handler : this.handlers) {
         handler.invalidate();
      }
   }
}

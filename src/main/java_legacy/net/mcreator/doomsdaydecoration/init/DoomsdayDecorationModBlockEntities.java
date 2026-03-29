package net.mcreator.doomsdaydecoration.init;

import net.mcreator.doomsdaydecoration.block.entity.AcrateBlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraft.world.level.block.entity.BlockEntityType.Builder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DoomsdayDecorationModBlockEntities {
   public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, "doomsday_decoration");
   public static final RegistryObject<BlockEntityType<?>> ACRATE = register("acrate", DoomsdayDecorationModBlocks.ACRATE, AcrateBlockEntity::new);

   private static RegistryObject<BlockEntityType<?>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntitySupplier<?> supplier) {
      return REGISTRY.register(registryname, () -> Builder.getOrCreateTag(supplier, new Block[]{(Block)block.get()}).setValue(null));
   }
}

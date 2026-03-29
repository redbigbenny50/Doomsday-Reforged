package net.mcreator.doomsdaydecoration.init;

import net.mcreator.doomsdaydecoration.DoomsdayDecorationMod;
import net.mcreator.doomsdaydecoration.block.entity.AcrateBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DoomsdayDecorationModBlockEntities {
  public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, DoomsdayDecorationMod.MODID);

  public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<AcrateBlockEntity>> ACRATE = REGISTRY.register(
      "acrate",
      () -> BlockEntityType.Builder.of(AcrateBlockEntity::new, DoomsdayDecorationModBlocks.ACRATE.get()).build(null)
  );
}

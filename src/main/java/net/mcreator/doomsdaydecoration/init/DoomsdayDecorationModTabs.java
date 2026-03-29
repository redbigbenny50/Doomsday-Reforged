package net.mcreator.doomsdaydecoration.init;

import net.mcreator.doomsdaydecoration.DoomsdayDecorationMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DoomsdayDecorationModTabs {
  public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DoomsdayDecorationMod.MODID);

  public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DOOMSDAY_DECORATION_BLOCK = REGISTRY.register(
      "doomsday_decoration_block",
      () -> CreativeModeTab.builder()
          .title(Component.translatable("item_group.doomsday_decoration.doomsday_decoration_block"))
          .icon(() -> new ItemStack(DoomsdayDecorationModBlocks.ACRATE.get()))
          .displayItems((params, output) -> {
            output.accept(DoomsdayDecorationModItems.PEACE.get());
            DoomsdayDecorationModItems.BLOCK_ITEMS.forEach(item -> output.accept(item.get()));
          })
          .build()
  );
}

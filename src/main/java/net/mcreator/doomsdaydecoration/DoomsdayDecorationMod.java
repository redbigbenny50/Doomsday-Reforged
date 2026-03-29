package net.mcreator.doomsdaydecoration;

import net.mcreator.doomsdaydecoration.block.entity.AcrateBlockEntity;
import net.mcreator.doomsdaydecoration.init.DoomsdayDecorationModBlockEntities;
import net.mcreator.doomsdaydecoration.init.DoomsdayDecorationModBlocks;
import net.mcreator.doomsdaydecoration.init.DoomsdayDecorationModItems;
import net.mcreator.doomsdaydecoration.init.DoomsdayDecorationModSounds;
import net.mcreator.doomsdaydecoration.init.DoomsdayDecorationModTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;

@Mod(DoomsdayDecorationMod.MODID)
public class DoomsdayDecorationMod {
  public static final String MODID = "doomsday_decoration";

  public DoomsdayDecorationMod(IEventBus modEventBus) {
    DoomsdayDecorationModBlocks.REGISTRY.register(modEventBus);
    DoomsdayDecorationModItems.REGISTRY.register(modEventBus);
    DoomsdayDecorationModBlockEntities.REGISTRY.register(modEventBus);
    DoomsdayDecorationModSounds.REGISTRY.register(modEventBus);
    DoomsdayDecorationModTabs.REGISTRY.register(modEventBus);
    modEventBus.addListener(DoomsdayDecorationMod::registerCapabilities);
  }

  private static void registerCapabilities(RegisterCapabilitiesEvent event) {
    event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DoomsdayDecorationModBlockEntities.ACRATE.get(), (AcrateBlockEntity be, net.minecraft.core.Direction side) -> new SidedInvWrapper(be, side));
  }
}

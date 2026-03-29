package net.mcreator.doomsdaydecoration.init;

import net.mcreator.doomsdaydecoration.DoomsdayDecorationMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DoomsdayDecorationModSounds {
  public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, DoomsdayDecorationMod.MODID);

  public static final DeferredHolder<SoundEvent, SoundEvent> HUZAI = REGISTRY.register("huzai", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DoomsdayDecorationMod.MODID, "huzai")));
  public static final DeferredHolder<SoundEvent, SoundEvent> OTTO = REGISTRY.register("otto", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DoomsdayDecorationMod.MODID, "otto")));
  public static final DeferredHolder<SoundEvent, SoundEvent> HHH = REGISTRY.register("hhh", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DoomsdayDecorationMod.MODID, "hhh")));
  public static final DeferredHolder<SoundEvent, SoundEvent> GGG = REGISTRY.register("ggg", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DoomsdayDecorationMod.MODID, "ggg")));
  public static final DeferredHolder<SoundEvent, SoundEvent> ROBLOX = REGISTRY.register("roblox", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(DoomsdayDecorationMod.MODID, "roblox")));
}

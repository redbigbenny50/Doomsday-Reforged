package net.mcreator.doomsdaydecoration.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;

public class DoomsdayDecorationModSounds {
   public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, "doomsday_decoration");
   public static final DeferredHolder<SoundEvent, SoundEvent> HUZAI = REGISTRY.register(
      "huzai", () -> SoundEvent.inflate(new ResourceLocation("doomsday_decoration", "huzai"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> OTTO = REGISTRY.register(
      "otto", () -> SoundEvent.inflate(new ResourceLocation("doomsday_decoration", "otto"))
   );
   public static final DeferredHolder<SoundEvent, SoundEvent> HHH = REGISTRY.register("hhh", () -> SoundEvent.inflate(new ResourceLocation("doomsday_decoration", "hhh")));
   public static final DeferredHolder<SoundEvent, SoundEvent> GGG = REGISTRY.register("ggg", () -> SoundEvent.inflate(new ResourceLocation("doomsday_decoration", "ggg")));
   public static final DeferredHolder<SoundEvent, SoundEvent> ROBLOX = REGISTRY.register(
      "roblox", () -> SoundEvent.inflate(new ResourceLocation("doomsday_decoration", "roblox"))
   );
}

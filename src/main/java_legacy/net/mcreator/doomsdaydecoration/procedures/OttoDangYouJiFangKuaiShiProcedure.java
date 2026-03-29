package net.mcreator.doomsdaydecoration.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.registries.BuiltInRegistries;

public class OttoDangYouJiFangKuaiShiProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if (world instanceof Level _level) {
         if (!_level.entityInside()) {
            _level.isClientSide(
               null,
               BlockPos.move(x, y, z),
               (SoundEvent)BuiltInRegistries.SOUND_EVENT.getValue(new ResourceLocation("doomsday_decoration:otto")),
               SoundSource.NEUTRAL,
               1.0F,
               1.0F
            );
         } else {
            _level.getBiome(
               x,
               y,
               z,
               (SoundEvent)BuiltInRegistries.SOUND_EVENT.getValue(new ResourceLocation("doomsday_decoration:otto")),
               SoundSource.NEUTRAL,
               1.0F,
               1.0F,
               false
            );
         }
      }
   }
}

package net.mcreator.doomsdaydecoration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.mcreator.doomsdaydecoration.init.DoomsdayDecorationModBlockEntities;
import net.mcreator.doomsdaydecoration.init.DoomsdayDecorationModBlocks;
import net.mcreator.doomsdaydecoration.init.DoomsdayDecorationModItems;
import net.mcreator.doomsdaydecoration.init.DoomsdayDecorationModSounds;
import net.mcreator.doomsdaydecoration.init.DoomsdayDecorationModTabs;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.MinecraftForge;
import net.neoforged.neoforge.event.TickEvent.Phase;
import net.neoforged.neoforge.event.TickEvent.ServerTickEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.fml.util.thread.SidedThreadGroups;
import net.neoforged.neoforge.network.NetworkRegistry;
import net.neoforged.neoforge.network.NetworkEvent.Context;
import net.neoforged.neoforge.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("doomsday_decoration")
public class DoomsdayDecorationMod {
   public static final Logger LOGGER = LogManager.getLogger(DoomsdayDecorationMod.class);
   public static final String MODID = "doomsday_decoration";
   private static final String PROTOCOL_VERSION = "1";
   public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(
      new ResourceLocation("doomsday_decoration", "doomsday_decoration"), () -> "1", "1"::equals, "1"::equals
   );
   private static int messageID = 0;
   private static final Collection<SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

   public DoomsdayDecorationMod() {
      MinecraftForge.EVENT_BUS.register(this);
      IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
      DoomsdayDecorationModSounds.REGISTRY.register(bus);
      DoomsdayDecorationModBlocks.REGISTRY.register(bus);
      DoomsdayDecorationModBlockEntities.REGISTRY.register(bus);
      DoomsdayDecorationModItems.REGISTRY.register(bus);
      DoomsdayDecorationModTabs.REGISTRY.register(bus);
   }

   public static <T> void addNetworkMessage(
      Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<Context>> messageConsumer
   ) {
      PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
      messageID++;
   }

   public static void queueServerWork(int tick, Runnable action) {
      if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER) {
         workQueue.add(new SimpleEntry<>(action, tick));
      }
   }

   @SubscribeEvent
   public void tick(ServerTickEvent event) {
      if (event.phase == Phase.END) {
         List<SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
         workQueue.forEach(work -> {
            work.setValue(work.getValue() - 1);
            if (work.getValue() == 0) {
               actions.add((SimpleEntry<Runnable, Integer>)work);
            }
         });
         actions.forEach(e -> e.getKey().run());
         workQueue.removeAll(actions);
      }
   }
}

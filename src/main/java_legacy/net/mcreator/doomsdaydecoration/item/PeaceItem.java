package net.mcreator.doomsdaydecoration.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item.Properties;

public class PeaceItem extends Item {
   public PeaceItem() {
      super(new Properties().playerWillDestroy(64).setPlacedBy(Rarity.EPIC));
   }
}

package net.mcreator.doomsdaydecoration.block;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

final class LegacyShapeRegistry {
  private static final Map<ResourceLocation, ShapeSet> SHAPES = new HashMap<>();

  static {
    LegacyShapeRegistryPart01.register();
    LegacyShapeRegistryPart02.register();
    LegacyShapeRegistryPart03.register();
    LegacyShapeRegistryPart04.register();
    LegacyShapeRegistryPart05.register();
    LegacyShapeRegistryPart06.register();
    LegacyShapeRegistryPart07.register();
    LegacyShapeRegistryPart08.register();
    LegacyShapeRegistryPart09.register();
    LegacyShapeRegistryPart10.register();
  }

  private LegacyShapeRegistry() {}

  static VoxelShape getShape(BlockState state, Direction direction) {
    ResourceLocation key = state.getBlock().builtInRegistryHolder().key().location();
    ShapeSet set = SHAPES.get(key);
    return set == null ? null : set.get(direction);
  }

  static void register(String id, VoxelShape fallback, Object... args) {
    ShapeSet set = new ShapeSet(fallback);
    for (int i = 0; i + 1 < args.length; i += 2) {
      if (!(args[i] instanceof Direction direction) || !(args[i + 1] instanceof VoxelShape shape)) {
        continue;
      }
      put(set, direction, shape);
    }
    SHAPES.put(ResourceLocation.fromNamespaceAndPath("doomsday_decoration", id), set);
  }

  private static void put(ShapeSet set, Direction direction, VoxelShape shape) {
    if (direction != null && shape != null) {
      set.shapes.put(direction, shape);
    }
  }

  static VoxelShape shape(VoxelShape... shapes) {
    VoxelShape result = Shapes.empty();
    for (VoxelShape shape : shapes) {
      result = Shapes.or(result, shape);
    }
    return result;
  }

  static VoxelShape box(double x1, double y1, double z1, double x2, double y2, double z2) {
    return Block.box(x1, y1, z1, x2, y2, z2);
  }

  static final class ShapeSet {
    private final EnumMap<Direction, VoxelShape> shapes = new EnumMap<>(Direction.class);
    private final VoxelShape fallback;

    private ShapeSet(VoxelShape fallback) {
      this.fallback = fallback;
    }

    private VoxelShape get(Direction direction) {
      VoxelShape shape = shapes.get(direction);
      return shape != null ? shape : fallback;
    }
  }
}

package net.mcreator.doomsdaydecoration.block;

import com.mojang.serialization.MapCodec;
import java.util.function.Supplier;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class SoundOnUseBlock extends SimpleHorizontalDirectionalBlock {
  public static final MapCodec<SoundOnUseBlock> CODEC = simpleCodec(SoundOnUseBlock::new);
  private final Supplier<SoundEvent> sound;

  public SoundOnUseBlock(Properties properties) {
    this(SoundEvents.UI_BUTTON_CLICK::value, properties);
  }

  public SoundOnUseBlock(Supplier<SoundEvent> sound, Properties properties) {
    super(properties);
    this.sound = sound;
  }

  @Override
  protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
    return CODEC;
  }

  @Override
  protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
    if (!level.isClientSide()) {
      level.playSound(null, pos, sound.get(), SoundSource.NEUTRAL, 1.0F, 1.0F);
    }
    return InteractionResult.sidedSuccess(level.isClientSide());
  }

  @Override
  protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
    if (!level.isClientSide()) {
      level.playSound(null, pos, sound.get(), SoundSource.NEUTRAL, 1.0F, 1.0F);
    }
    return ItemInteractionResult.sidedSuccess(level.isClientSide());
  }
}

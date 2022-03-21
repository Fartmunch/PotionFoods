package cfrishausen.potionfoods.blocks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;

public class PotionFoodCake extends CakeBlock {
    public PotionFoodCake(Properties p_i48527_2_, Potion potion) {
        super(p_i48527_2_);
        this.potion = potion;
    }

    protected final Potion potion;

    public Potion getPotion() {
        return potion;

    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player playerIn, InteractionHand hand, BlockHitResult p_225533_6_) {
        if (level.isClientSide) {
            ItemStack itemstack = playerIn.getItemInHand(hand);
            if (this.eatPotionCake(level, pos, state, playerIn).consumesAction()) {
                return InteractionResult.SUCCESS;
            }

            if (itemstack.isEmpty()) {
                return InteractionResult.CONSUME;
            }
        }

        return this.eatPotionCake(level, pos, state, playerIn);
    }

    private InteractionResult eatPotionCake(LevelAccessor level, BlockPos pos, BlockState state, Player playerIn) {
        if (!playerIn.canEat(false)) {
            return InteractionResult.PASS;
        } else {
            playerIn.awardStat(Stats.EAT_CAKE_SLICE);
            playerIn.getFoodData().eat(2, 0.1F);

            //for (EffectInstance effect : potion potion.getEffects()) {
            //  playerIn.addEffect(effect);
            //}
            potion.getEffects().forEach(playerIn::addEffect);
            int i = state.getValue(BITES);
            if (i < 6) {
                level.setBlock(pos, state.setValue(BITES, Integer.valueOf(i + 1)), 3);
            } else {
                level.removeBlock(pos, false);
            }

            return InteractionResult.SUCCESS;
        }
    }
}

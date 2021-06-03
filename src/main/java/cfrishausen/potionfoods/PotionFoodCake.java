package cfrishausen.potionfoods;

import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class PotionFoodCake extends CakeBlock {
    public PotionFoodCake(Properties p_i48527_2_, Potion potion) {
        super(p_i48527_2_);
        this.potion = potion;
    }

    protected final Potion potion;

    public Potion getPotion() {
        return potion;

    }
    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity playerIn, Hand hand, BlockRayTraceResult p_225533_6_) {
        if (level.isClientSide) {
            ItemStack itemstack = playerIn.getItemInHand(hand);
            if (this.eat(level, pos, state, playerIn).consumesAction()) {
                return ActionResultType.SUCCESS;
            }

            if (itemstack.isEmpty()) {
                return ActionResultType.CONSUME;
            }
        }

        return this.eat(level, pos, state, playerIn);
    }

    private ActionResultType eat(IWorld level, BlockPos pos, BlockState state, PlayerEntity playerIn) {
        if (!playerIn.canEat(false)) {
            return ActionResultType.PASS;
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

            return ActionResultType.SUCCESS;
        }
    }
}

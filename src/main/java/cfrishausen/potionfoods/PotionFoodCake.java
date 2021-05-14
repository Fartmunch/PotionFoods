package cfrishausen.potionfoods;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.potion.Potion;

public class PotionFoodCake extends BlockItem {
    public PotionFoodCake(Block p_i48527_1_, Properties p_i48527_2_, Potion potion) {
        super(p_i48527_1_, p_i48527_2_);
        this.potion = potion;
    }

    protected final Potion potion;

    public Potion getPotion() {
        return potion;

    }
}

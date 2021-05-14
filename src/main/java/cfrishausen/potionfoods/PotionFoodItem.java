package cfrishausen.potionfoods;

import net.minecraft.potion.Potion;

public class PotionFoodItem extends net.minecraft.item.SimpleFoiledItem {

    protected final Potion potion;

    public Potion getPotion() {
        return potion;
    }

    public PotionFoodItem(Properties p_i48467_1_, Potion potion) {
        super(p_i48467_1_);
        this.potion = potion;
    }


}

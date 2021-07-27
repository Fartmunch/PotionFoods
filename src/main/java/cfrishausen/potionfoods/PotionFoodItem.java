package cfrishausen.potionfoods;

import net.minecraft.world.item.alchemy.Potion;

import net.minecraft.world.item.Item.Properties;

public class PotionFoodItem extends net.minecraft.world.item.SimpleFoiledItem {

    protected final Potion potion;

    public Potion getPotion() {
        return potion;
    }

    public PotionFoodItem(Properties p_i48467_1_, Potion potion) {
        super(p_i48467_1_);
        this.potion = potion;
    }


}

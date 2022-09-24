package cfrishausen.potionfoods.data;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.StrictNBTIngredient;

public class IngredientNBTWrapper extends StrictNBTIngredient {
    public IngredientNBTWrapper(ItemStack stack) {
        super(stack);
    }
}

package cfrishausen.potionfoods.data;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.NBTIngredient;

public class IngredientNBTWrapper extends NBTIngredient {
    public IngredientNBTWrapper(ItemStack stack) {
        super(stack);
    }
}

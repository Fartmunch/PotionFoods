package cfrishausen.potionfoods.items;

import cfrishausen.potionfoods.data.Data;
import cfrishausen.potionfoods.registry.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.RegistryObject;

public class PotionFoodCreativeTab {
    public static final CreativeModeTab FOODS_TAB = new CreativeModeTab("potionfoods_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.MENU_BREAD.get());
        }
    };
}

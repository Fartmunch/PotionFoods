package cfrishausen.potionfoods.data;

import cfrishausen.potionfoods.PotionFoodItem;
import cfrishausen.potionfoods.PotionFoods;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class Items extends ItemModelProvider {

    public Items(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, PotionFoods.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        for (RegistryObject<PotionFoodItem> item : Data.NEW_ITEMS) {
            singleTexture(item.get().getRegistryName().getPath(), new ResourceLocation("item/handheld"),
                    "layer0", new ResourceLocation("minecraft", "item/" + Data.BASE_FOOD_NAMES.get(item)));
        }
    }
}

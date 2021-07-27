package cfrishausen.potionfoods.data;

import cfrishausen.potionfoods.PotionFoodItem;
import cfrishausen.potionfoods.PotionFoods;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.BlockItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fmllegacy.RegistryObject;

public class PotionItemModelProvider extends ItemModelProvider {

    public PotionItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, PotionFoods.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        for (RegistryObject<PotionFoodItem> item : Data.NEW_ITEMS) {
            singleTexture(item.get().getRegistryName().getPath(), new ResourceLocation("item/generated"),
                    "layer0", new ResourceLocation("minecraft", "item/" + Data.BASE_FOOD_NAMES.get(item)));
        }

        for (RegistryObject<BlockItem> item : Data.NEW_CAKE_BLOCK_ITEMS) {
            singleTexture(item.get().getRegistryName().getPath(), new ResourceLocation("item/generated"),
                    "layer0", new ResourceLocation("potionfoods", "item/" + "cake"));
        }
    }
}

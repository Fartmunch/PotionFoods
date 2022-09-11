package cfrishausen.potionfoods.data;

import cfrishausen.potionfoods.items.PotionFoodItem;
import cfrishausen.potionfoods.PotionFoods;
import cfrishausen.potionfoods.registry.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.BlockItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class PotionItemModelProvider extends ItemModelProvider {

    public PotionItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, PotionFoods.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        singleTexture(ModItems.MENU_BREAD.getId().getPath(), new ResourceLocation("item/generated"),
                "layer0", new ResourceLocation("minecraft", "item/" + "bread"));

        for (RegistryObject<PotionFoodItem> item : Data.NEW_ITEMS) {
            singleTexture(Registry.ITEM.getKey(item.get()).getPath(), new ResourceLocation("item/generated"),
                    "layer0", new ResourceLocation("minecraft", "item/" + Data.BASE_FOOD_NAMES.get(item)));
        }

        for (RegistryObject<BlockItem> item : Data.NEW_CAKE_BLOCK_ITEMS) {
            singleTexture(Registry.ITEM.getKey(item.get()).getPath(), new ResourceLocation("item/generated"),
                    "layer0", new ResourceLocation("potionfoods", "item/" + "cake"));
        }
    }
}

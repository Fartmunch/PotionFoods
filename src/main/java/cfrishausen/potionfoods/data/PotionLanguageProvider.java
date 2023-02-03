package cfrishausen.potionfoods.data;

import cfrishausen.potionfoods.items.PotionFoodItem;
import cfrishausen.potionfoods.PotionFoods;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.text.WordUtils;

public class PotionLanguageProvider extends LanguageProvider {

    public PotionLanguageProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PotionFoods.MOD_ID, "en_us");
    }

    // Used to make the player visible name in the correct syntax.
    public static String convertSyntax(String food, String effect, Boolean isCake) {
        if (!isCake) {
            String foodSpace = food.replace("_", " ");
            String effectSpace = effect.replace("_", " ");
            String combinedStrings = foodSpace + " of " + effectSpace;
            String withCap = WordUtils.capitalizeFully(combinedStrings);
            String finalName = withCap.replace(" Of ", " of ");
            if (food.equals("porkchop") || food.equals("cod") || food.equals("salmon") || food.equals("beef") || food.equals("chicken") || food.equals("rabbit") || food.equals("mutton")) {
                String rawFinalName = "Raw " + finalName;
                return rawFinalName;
            } else {
                return finalName;
            }
        } else {
            String cakeEffectSpace = food.replace("_cake_item", "");
            String cakeEffectSpace2 = cakeEffectSpace.replace("_", " ");
            String cakeCombinedName = "cake of " + cakeEffectSpace2;
            String cakeCamelCase = WordUtils.capitalizeFully(cakeCombinedName);
            String cakeFinalName = cakeCamelCase.replace(" Of ", " of ");
            return cakeFinalName;
        }
    }

    @Override
    protected void addTranslations() {

        for (RegistryObject<PotionFoodItem> foodItem : Data.NEW_ITEMS) {
            addItem(foodItem, convertSyntax(Data.BASE_FOOD_NAMES.get(foodItem), Data.EFFECT_NAMES.get(foodItem), false));
        }

        for (RegistryObject<BlockItem> createdCake : Data.NEW_CAKE_BLOCK_ITEMS) {
            addItem(createdCake, convertSyntax(createdCake.getId().getPath(), "", true));
        }

        add("item_group.potionfoods.main", "Potion Foods");
    }
}
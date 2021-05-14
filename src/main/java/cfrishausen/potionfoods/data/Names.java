package cfrishausen.potionfoods.data;

import cfrishausen.potionfoods.PotionFoodItem;
import cfrishausen.potionfoods.PotionFoods;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.RegistryObject;
import org.apache.commons.lang3.text.WordUtils;

public class Names extends LanguageProvider {

    public Names(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, PotionFoods.MOD_ID, "en_us");
    }

    // Used to make the player visible name in the correct syntax.
    public static String convertSyntax(String food, String effect) {
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
    }

    @Override
    protected void addTranslations() {
        for (RegistryObject<PotionFoodItem> foodItem : Data.NEW_ITEMS)
            addItem(foodItem, convertSyntax(Data.BASE_FOOD_NAMES.get(foodItem), Data.EFFECT_NAMES.get(foodItem)));
    }
}

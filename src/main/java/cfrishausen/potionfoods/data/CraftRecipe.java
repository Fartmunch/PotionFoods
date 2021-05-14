package cfrishausen.potionfoods.data;

import cfrishausen.potionfoods.PotionFoodItem;
import com.google.common.collect.Maps;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.data.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.datafix.fixes.TileEntityId;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.RegistryObject;


import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CraftRecipe extends RecipeProvider {
    public CraftRecipe(DataGenerator generatorIn) { super(generatorIn); }

    // Turns the potion in to an ingredient that can be used for crafting.
    private static Ingredient makePotionIngredient(Potion potion) {
        ItemStack swiftnessPotion = new ItemStack(Items.POTION);
        PotionUtils.setPotion(swiftnessPotion, potion);
        return new IngredientNBTWrapper(swiftnessPotion);
    }
    // Map that can identify the correct ingredient for crafting based off of the food.
    public static final Map<Potion, Ingredient> FOOD_INGREDIENTS = Maps.newHashMap();


    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {

        // Enters in the new ingredient into the map with key effect.
        for (Potion newEffect : Data.EFFECTS) {
            FOOD_INGREDIENTS.put(newEffect, makePotionIngredient(newEffect));
        }

        // Access the food ingredient inputting each game item as key in FOOD_INGREDIENTS and uses it to make the recipe
         for (RegistryObject<PotionFoodItem> newItem : Data.NEW_ITEMS) {
             Ingredient newIngredient = FOOD_INGREDIENTS.get(newItem.get().getPotion());
             Item baseFood = Data.BASE_FOODS.get(newItem);
             createCraftRecipe(newItem, baseFood, newIngredient, consumer);
         }
         createSmeltRecipes(consumer);
    }

    public static void createCraftRecipe(RegistryObject<PotionFoodItem> object, Item baseFood, Ingredient ingredient, Consumer<IFinishedRecipe> subConsumer){
        ShapedRecipeBuilder.shaped(object.get(), 8)
                .pattern("xxx")
                .pattern("xyx")
                .pattern("xxx")
                .define('x', baseFood.getItem())
                .define('y', ingredient)
                .group("potionfoods")
                .unlockedBy("has_item", has(baseFood.getItem()))
                .save(subConsumer);
        //Items.BREAD.getItem()
    }

    public static void createSmeltRecipes(Consumer<IFinishedRecipe> subConsumer) {

        Map<Item, Item> foodToCooked = Maps.newHashMap();
        foodToCooked.put(Items.PORKCHOP, Items.COOKED_PORKCHOP);
        foodToCooked.put(Items.COD, Items.COOKED_COD);
        foodToCooked.put(Items.SALMON, Items.COOKED_SALMON);
        foodToCooked.put(Items.BEEF, Items.COOKED_BEEF);
        foodToCooked.put(Items.CHICKEN, Items.COOKED_CHICKEN);
        foodToCooked.put(Items.RABBIT, Items.COOKED_RABBIT);
        foodToCooked.put(Items.MUTTON, Items.COOKED_MUTTON);
        foodToCooked.put(Items.POTATO, Items.BAKED_POTATO);


        for (RegistryObject<PotionFoodItem> object : Data.NEW_ITEMS) {
            if (foodToCooked.containsKey(Data.BASE_FOODS.get(object))) {
                CookingRecipeBuilder.smelting(Ingredient.of(object.get()), foodToCooked.get(Data.BASE_FOODS.get(object)), 0.35F, 200)
                        .unlockedBy("has_item", has(object.get()))
                        .save(subConsumer, object.get().getRegistryName().getPath());
                CookingRecipeBuilder.cooking(Ingredient.of(object.get()), foodToCooked.get(Data.BASE_FOODS.get(object)), 0.35F, 100, IRecipeSerializer.SMOKING_RECIPE)
                        .unlockedBy("has_item", has(object.get()))
                        .save(subConsumer, object.get().getRegistryName().getPath() + "_from_smoking");
            }
        }


//        CookingRecipeBuilder.smelting(Ingredient.of(Items.PORKCHOP), Items.COOKED_PORKCHOP, 0.35F, 200)
//                    .unlockedBy("has_item", has(Items.PORKCHOP.getItem()))
//                    .save(subConsumer);
    }




}

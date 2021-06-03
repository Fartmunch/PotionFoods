package cfrishausen.potionfoods.data;

import cfrishausen.potionfoods.PotionFoodCake;
import cfrishausen.potionfoods.PotionFoodItem;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.data.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.fml.RegistryObject;


import java.util.Map;
import java.util.function.Consumer;

public class PotionRecipeProvider extends RecipeProvider {
    public PotionRecipeProvider(DataGenerator generatorIn) { super(generatorIn); }

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
             createShapedRecipe(newItem, baseFood, newIngredient, consumer);
         }

         // Creates recipies for new cakes
         for (RegistryObject<BlockItem> newCakeItem : Data.NEW_CAKE_BLOCK_ITEMS) {
             // Converts a cake block item into its matching cake block.
             Block modCake = newCakeItem.get().getBlock();
             Ingredient ingredient = null;
             // Sets the potion item as the ingredient for the craft based on map with key cake blocks and value potion effects.
             // From there the effect is applied to food ingredient map which has value nbt wrappers to access in game potion items.
             for (RegistryObject<Block> block : Data.CAKES_WITH_POTIONS.keySet()) {
                 if (block.get() == modCake) {
                     ingredient = FOOD_INGREDIENTS.get(Data.CAKES_WITH_POTIONS.get(block));
                 }
             }
             ShapelessRecipeBuilder.shapeless(newCakeItem.get(), 1)
                     .requires(ingredient)
                     .requires(Items.CAKE)
                     .group("potionfoods")
                     .unlockedBy("has_item", has(Items.CAKE))
                     .save(consumer);

         }
         createSmeltRecipes(consumer);
    }

    public static void createShapedRecipe(RegistryObject<PotionFoodItem> object, Item baseFood, Ingredient ingredient, Consumer<IFinishedRecipe> subConsumer){
        ShapedRecipeBuilder.shaped(object.get(), 8)
                .pattern("xxx")
                .pattern("xyx")
                .pattern("xxx")
                .define('x', baseFood.getItem())
                .define('y', ingredient)
                .group("potionfoods")
                .unlockedBy("has_item", has(baseFood.getItem()))
                .save(subConsumer);
    }


    public static void createSmeltRecipes(Consumer<IFinishedRecipe> subConsumer) {
        // Relates non cooked food to the proper cooked food.
        Map<Item, Item> foodToCooked = Maps.newHashMap();
        foodToCooked.put(Items.PORKCHOP, Items.COOKED_PORKCHOP);
        foodToCooked.put(Items.COD, Items.COOKED_COD);
        foodToCooked.put(Items.SALMON, Items.COOKED_SALMON);
        foodToCooked.put(Items.BEEF, Items.COOKED_BEEF);
        foodToCooked.put(Items.CHICKEN, Items.COOKED_CHICKEN);
        foodToCooked.put(Items.RABBIT, Items.COOKED_RABBIT);
        foodToCooked.put(Items.MUTTON, Items.COOKED_MUTTON);
        foodToCooked.put(Items.POTATO, Items.BAKED_POTATO);

        // Creates a furnace and smoker recipe for each new item, not including cakes.
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
    }




}

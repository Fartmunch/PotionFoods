package cfrishausen.potionfoods.data;

import cfrishausen.potionfoods.items.PotionFoodItem;
import cfrishausen.potionfoods.registry.ModItems;
import com.google.common.collect.Maps;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;


import java.util.Map;
import java.util.function.Consumer;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraftforge.registries.RegistryObject;

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
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

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

    public static void createShapedRecipe(RegistryObject<PotionFoodItem> object, Item baseFood, Ingredient ingredient, Consumer<FinishedRecipe> subConsumer){
        ShapedRecipeBuilder.shaped(object.get(), 8)
                .pattern("xxx")
                .pattern("xyx")
                .pattern("xxx")
                .define('x', baseFood.asItem())
                .define('y', ingredient)
                .group("potionfoods")
                .unlockedBy("has_item", has(baseFood.asItem()))
                .save(subConsumer);
    }


    public static void createSmeltRecipes(Consumer<FinishedRecipe> subConsumer) {
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
                SimpleCookingRecipeBuilder.smelting(Ingredient.of(object.get()), foodToCooked.get(Data.BASE_FOODS.get(object)), 0.35F, 200)
                        .unlockedBy("has_item", has(object.get()))
                        .save(subConsumer, object.getId().getPath()); // CHANGE
                SimpleCookingRecipeBuilder.smoking(Ingredient.of(object.get()), foodToCooked.get(Data.BASE_FOODS.get(object)), 0.35F, 100)
                        .unlockedBy("has_item", has(object.get()))
                        .save(subConsumer, object.getId().getPath() + "_from_smoking"); // CHANGE
                SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(object.get()), foodToCooked.get(Data.BASE_FOODS.get(object)), 0.35F, 100)
                        .unlockedBy("has_item", has(object.get()))
                        .save(subConsumer, object.getId().getPath() + "_from_campfire_cooking"); // CHANGE
            }
        }
    }




}

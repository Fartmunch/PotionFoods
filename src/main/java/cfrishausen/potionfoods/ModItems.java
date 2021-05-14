package cfrishausen.potionfoods;

import cfrishausen.potionfoods.data.Data;
import com.google.common.collect.Lists;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.rmi.registry.Registry;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PotionFoods.MOD_ID);
    // Creates two lists, one for the food items and one for the potion effects.
    public static final List<Item> FOODS = Lists.newArrayList(
            Items.APPLE,
            Items.MUSHROOM_STEW,
            Items.BREAD,
            Items.PORKCHOP,
            Items.COOKED_PORKCHOP,
            Items.GOLDEN_APPLE,
            Items.COD,
            Items.SALMON,
            Items.TROPICAL_FISH,
            Items.PUFFERFISH,
            Items.COOKED_COD,
            Items.COOKED_SALMON,
            Items.COOKIE,
            Items.MELON_SLICE,
            Items.DRIED_KELP,
            Items.BEEF,
            Items.COOKED_BEEF,
            Items.CHICKEN,
            Items.COOKED_CHICKEN,
            Items.ROTTEN_FLESH,
            Items.SPIDER_EYE,
            Items.CARROT,
            Items.POTATO,
            Items.BAKED_POTATO,
            Items.POISONOUS_POTATO,
            Items.PUMPKIN_PIE,
            Items.RABBIT,
            Items.COOKED_RABBIT,
            Items.RABBIT_STEW,
            Items.MUTTON,
            Items.COOKED_MUTTON,
            Items.BEETROOT,
            Items.BEETROOT_SOUP,
            Items.SWEET_BERRIES,
            Items.HONEY_BOTTLE
            );

    public static final List<Potion> POTION_EFFECTS = Lists.newArrayList(
            Potions.MUNDANE,
            Potions.THICK,
            Potions.AWKWARD,
            Potions.NIGHT_VISION,
            Potions.LONG_NIGHT_VISION,
            Potions.INVISIBILITY,
            Potions.LONG_INVISIBILITY,
            Potions.LEAPING,
            Potions.LONG_LEAPING,
            Potions.STRONG_LEAPING,
            Potions.FIRE_RESISTANCE,
            Potions.LONG_FIRE_RESISTANCE,
            Potions.SWIFTNESS,
            Potions.LONG_SWIFTNESS,
            Potions.STRONG_SWIFTNESS,
            Potions.SLOWNESS,
            Potions.LONG_SLOWNESS,
            Potions.STRONG_SLOWNESS,
            Potions.TURTLE_MASTER,
            Potions.LONG_TURTLE_MASTER,
            Potions.STRONG_TURTLE_MASTER,
            Potions.WATER_BREATHING,
            Potions.LONG_WATER_BREATHING,
            Potions.HEALING,
            Potions.STRONG_HEALING,
            Potions.HARMING,
            Potions.STRONG_HARMING,
            Potions.POISON,
            Potions.LONG_POISON,
            Potions.STRONG_POISON,
            Potions.REGENERATION,
            Potions.LONG_REGENERATION,
            Potions.STRONG_REGENERATION,
            Potions.STRENGTH,
            Potions.LONG_STRENGTH,
            Potions.STRONG_STRENGTH,
            Potions.WEAKNESS,
            Potions.LONG_WEAKNESS,
            Potions.LUCK,
            Potions.SLOW_FALLING,
            Potions.LONG_SLOW_FALLING
            );

    // Function to create food in registry
    public static RegistryObject<PotionFoodItem> createFood(String name, int nutritionValue, float saturationValue, Supplier<EffectInstance> effect, Potion potion) {
        return ITEMS.register(name, () -> {
            return new PotionFoodItem(new Item.Properties().food(new Food.Builder().nutrition(nutritionValue).saturationMod(saturationValue).effect(effect, 1.0F).build()).tab(ItemGroup.TAB_FOOD), potion);
        });

    }

    public static RegistryObject<BlockItem> createCakeItem(String name, RegistryObject<Block> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ItemGroup.TAB_FOOD)));
    }

//    public static RegistryObject<BlockItem> createCakeItem(String name, RegistryObject<Block> block) {
//        return ITEMS.register(name, () -> {
//            return new BlockItem(block.get(), new Item.Properties().tab(ItemGroup.TAB_FOOD));
//        });
//    }

    // Loops over each item in FOODS and then applies each effect from POTION_EFFECTS.
    // Registers the object and sends it to Data class
    // Also sends base food and names into Data class maps
    static {
        for (Item item : FOODS) {
            String foodName = item.getRegistryName().getPath();
            Food food = item.getFoodProperties();

            for (Potion potion : POTION_EFFECTS) {
                String potionName = potion.getRegistryName().getPath();
                // Makes an item name matching minecraft naming convention
                String itemName = potionName + "_" + foodName;
                RegistryObject<PotionFoodItem> object = createFood(itemName, food.getNutrition(), food.getSaturationModifier(), () -> {
                    return potion.getEffects().get(0);
                }, potion);
                Data.NEW_ITEMS.add(object);
                Data.BASE_FOODS.put(object, item);
                // Adds base food and effect names to Data for use in generated files.
                Data.BASE_FOOD_NAMES.put(object, foodName);
                Data.EFFECT_NAMES.put(object, potionName);
            }
        }

        for (RegistryObject<Block> cakeBlock: Data.NEW_CAKE_BLOCKS) {
            RegistryObject<BlockItem> cakeBlockItem = createCakeItem(cakeBlock.getId()+"_item", cakeBlock);
            Data.NEW_CAKE_BLOCK_ITEMS.add(cakeBlockItem);
            System.out.println(cakeBlockItem.getId() + "JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ");

        }
    }

    public static final RegistryObject<BlockItem> TEST_ITEM = ITEMS.register("test_item",
            () -> new BlockItem(ModBlocks.TEST.get(), new Item.Properties().tab(ItemGroup.TAB_FOOD)));



}
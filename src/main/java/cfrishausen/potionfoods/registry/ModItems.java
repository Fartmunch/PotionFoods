package cfrishausen.potionfoods.registry;

import cfrishausen.potionfoods.PotionFoods;
import cfrishausen.potionfoods.data.Data;
import cfrishausen.potionfoods.items.PotionFoodItem;
import com.google.common.collect.Lists;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Supplier;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

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
            Items.GLOW_BERRIES,
            Items.HONEY_BOTTLE,
            Items.GOLDEN_CARROT
            );

    public static final List<Potion> POTION_EFFECTS = Lists.newArrayList(
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

    public static final RegistryObject<PotionFoodItem> MENU_BREAD = ITEMS.register("menu_bread", () -> new PotionFoodItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(1).effect( () -> {
        return Potions.SWIFTNESS.getEffects().get(0);
    }, 1.0F).build()), Potions.SWIFTNESS));

    // Function to create food in registry
    public static RegistryObject<PotionFoodItem> createFood(String name, int nutritionValue, float saturationValue, Supplier<MobEffectInstance> effect, Potion potion) {
        return ITEMS.register(name, () -> {
            return new PotionFoodItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(nutritionValue).saturationMod(saturationValue).effect(effect, 1.0F).build()), potion);
        });

    }

    public static RegistryObject<PotionFoodItem> createGoldenCarrot(String name, int nutritionValue, float saturationValue, Supplier<MobEffectInstance> effect, Potion potion) {
        return ITEMS.register(name, () -> {
            return new PotionFoodItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(nutritionValue).saturationMod(saturationValue).effect(effect, 1.0F).build()), potion);
        });

    }

    public static RegistryObject<PotionFoodItem> createGoldenApple(String name, int nutritionValue, float saturationValue, Supplier<MobEffectInstance> effect, Potion potion) {
        return ITEMS.register(name, () -> {
            return new PotionFoodItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(nutritionValue).saturationMod(saturationValue).effect(effect, 1.0F).effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1), 1.0F).effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0), 1.0F).alwaysEat().build()), potion);
        });

    }

    public static RegistryObject<PotionFoodItem> createTurtleFood(String name, int nutritionValue, float saturationValue, Supplier<MobEffectInstance> effect, Supplier<MobEffectInstance> effect2, Potion potion) {
        return ITEMS.register(name, () -> {
            return new PotionFoodItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(nutritionValue).saturationMod(saturationValue).effect(effect, 1.0F).effect(effect2, 1.0F).build()), potion);
        });

    }

    public static RegistryObject<PotionFoodItem> createTurtleGoldenCarrot(String name, int nutritionValue, float saturationValue, Supplier<MobEffectInstance> effect, Supplier<MobEffectInstance> effect2, Potion potion) {
        return ITEMS.register(name, () -> {
            return new PotionFoodItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(nutritionValue).saturationMod(saturationValue).effect(effect, 1.0F).effect(effect2, 1.0F).build()), potion);
        });

    }

    public static RegistryObject<PotionFoodItem> createTurtleGoldenApple(String name, int nutritionValue, float saturationValue, Supplier<MobEffectInstance> effect, Supplier<MobEffectInstance> effect2, Potion potion) {
        return ITEMS.register(name, () -> {
            return new PotionFoodItem(new Item.Properties().food(new FoodProperties.Builder().nutrition(nutritionValue).saturationMod(saturationValue).effect(effect, 1.0F).effect(effect2, 1.0F).effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1), 1.0F).effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 0), 1.0F).alwaysEat().build()), potion);
        });

    }

    public static RegistryObject<BlockItem> createCakeItem(String name, RegistryObject<Block> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    // Loops over each item in FOODS and then applies each effect from POTION_EFFECTS.
    // Registers the object and sends it to Data class
    // Also sends base food and names into Data class maps
    public static void init() {
        ModItems.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());


        for (Item item : FOODS) {
            String foodName = ForgeRegistries.ITEMS.getKey(item).getPath();
            FoodProperties food = item.getFoodProperties();

            for (Potion potion : POTION_EFFECTS) {
                String potionName = ForgeRegistries.POTIONS.getKey(potion).getPath();
                // Makes an item name matching minecraft naming convention
                String itemName = potionName + "_" + foodName;
                Supplier<MobEffectInstance> effectInstanceSupplier = null;
                RegistryObject<PotionFoodItem> object = null;
                // Makes sure that the Turtle potions get both potion effect instances
                if (potion == Potions.TURTLE_MASTER || potion == Potions.LONG_TURTLE_MASTER || potion == Potions.STRONG_TURTLE_MASTER) {
                    if (item == Items.GOLDEN_APPLE) {
                        object = createTurtleGoldenApple(itemName, food.getNutrition(), food.getSaturationModifier(), () -> {
                            return potion.getEffects().get(0);
                        }, () -> {
                            return potion.getEffects().get(1);
                        }, potion);
                    } else if (item == Items.GOLDEN_CARROT) {
                        object = createTurtleGoldenCarrot(itemName, food.getNutrition(), food.getSaturationModifier(), () -> {
                            return potion.getEffects().get(0);
                        }, () -> {
                            return potion.getEffects().get(1);
                        }, potion);
                    } else {
                        object = createTurtleFood(itemName, food.getNutrition(), food.getSaturationModifier(), () -> {
                            return potion.getEffects().get(0);
                        }, () -> {
                            return potion.getEffects().get(1);
                        }, potion);
                    }
                } else {
                    if (item == Items.GOLDEN_APPLE) {
                        object = createGoldenApple(itemName, food.getNutrition(), food.getSaturationModifier(), () -> {
                            return potion.getEffects().get(0);
                        }, potion);
                    } else if (item == Items.GOLDEN_CARROT) {
                        object = createGoldenCarrot(itemName, food.getNutrition(), food.getSaturationModifier(), () -> {
                            return potion.getEffects().get(0);
                        }, potion);
                    } else {
                        object = createFood(itemName, food.getNutrition(), food.getSaturationModifier(), () -> {
                            return potion.getEffects().get(0);
                        }, potion);
                    }
                }






                Data.NEW_ITEMS.add(object);
                Data.BASE_FOODS.put(object, item);
                // Adds base food and effect names to Data for use in generated files.
                Data.BASE_FOOD_NAMES.put(object, foodName);
                Data.EFFECT_NAMES.put(object, potionName);
            }
        }

        for (RegistryObject<Block> cakeBlock: Data.NEW_CAKE_BLOCKS) {
            RegistryObject<BlockItem> cakeBlockItem = createCakeItem(cakeBlock.getId().getPath().replace("_block", "_item"), cakeBlock);
            Data.NEW_CAKE_BLOCK_ITEMS.add(cakeBlockItem);
        }
    }

    public static void addItemsToTab(CreativeModeTab.Output output) {
        // Add all standard items for creative tab
        for(RegistryObject<PotionFoodItem> item : Data.BASE_FOODS.keySet()) {
            output.accept(item.get());
        }

        // Add cake items to creative tab
        for(RegistryObject<BlockItem> cake : Data.NEW_CAKE_BLOCK_ITEMS) {
            output.accept(cake.get());
        }
    }
}
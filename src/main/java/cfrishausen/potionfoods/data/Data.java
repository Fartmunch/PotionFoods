package cfrishausen.potionfoods.data;

import cfrishausen.potionfoods.registry.ModItems;
import cfrishausen.potionfoods.items.PotionFoodItem;
import cfrishausen.potionfoods.PotionFoods;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;

//See examples for DataGeneration
//https://github.com/MinecraftForge/MinecraftForge/blob/1.16.x/src/test/java/net/minecraftforge/debug/DataGeneratorTest.java#L325-L652

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = PotionFoods.MOD_ID)
public class Data {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        gen.addProvider(true, new PotionRecipeProvider(gen));
        gen.addProvider(true, new PotionItemModelProvider(gen, helper));
        gen.addProvider(true, new PotionLanguageProvider(gen, helper));
        gen.addProvider(true, new PotionBlockStateProvider(gen, helper));
    }

    public static final List<RegistryObject<PotionFoodItem>> NEW_ITEMS = Lists.newArrayList();
    public static final List<RegistryObject<Block>> NEW_CAKE_BLOCKS = Lists.newArrayList();
    public static final List<RegistryObject<BlockItem>> NEW_CAKE_BLOCK_ITEMS = Lists.newArrayList();
    public static final List<Potion> EFFECTS = ModItems.POTION_EFFECTS;
    public static final Map<RegistryObject<PotionFoodItem>, Item> BASE_FOODS = Maps.newHashMap();
    public static final Map<RegistryObject<PotionFoodItem>, String> BASE_FOOD_NAMES = Maps.newHashMap();
    public static final Map<RegistryObject<PotionFoodItem>, String> EFFECT_NAMES = Maps.newHashMap();
    public static final Map<RegistryObject<Block>, Potion> CAKES_WITH_POTIONS = Maps.newHashMap();

}
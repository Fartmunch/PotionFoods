package cfrishausen.potionfoods.data;

import cfrishausen.potionfoods.ModBlocks;
import cfrishausen.potionfoods.ModItems;
import cfrishausen.potionfoods.PotionFoodCake;
import cfrishausen.potionfoods.PotionFoodItem;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Data {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        gen.addProvider(new CraftRecipe(gen));
        gen.addProvider(new Items(gen, event.getExistingFileHelper()));
        gen.addProvider(new Names(gen, event.getExistingFileHelper()));
    }

    public static final List<RegistryObject<PotionFoodItem>> NEW_ITEMS = Lists.newArrayList();
    public static final List<RegistryObject<Block>> NEW_CAKE_BLOCKS = Lists.newArrayList();
    public static final List<RegistryObject<BlockItem>> NEW_CAKE_BLOCK_ITEMS = Lists.newArrayList();
    public static final List<Potion> EFFECTS = ModItems.POTION_EFFECTS;
    public static final Map<RegistryObject<PotionFoodItem>, Item> BASE_FOODS = Maps.newHashMap();
    public static final Map<RegistryObject<PotionFoodItem>, String> BASE_FOOD_NAMES = Maps.newHashMap();
    public static final Map<RegistryObject<PotionFoodItem>, String> EFFECT_NAMES = Maps.newHashMap();



}

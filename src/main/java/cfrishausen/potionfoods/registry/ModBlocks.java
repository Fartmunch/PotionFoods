package cfrishausen.potionfoods.registry;

import cfrishausen.potionfoods.PotionFoods;
import cfrishausen.potionfoods.blocks.PotionFoodCake;
import cfrishausen.potionfoods.data.Data;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PotionFoods.MOD_ID);


    public static RegistryObject<Block> createCakeBlock(String name, Potion potion) {
        return BLOCKS.register(name, () -> new PotionFoodCake(BlockBehaviour.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.WOOL), potion));

    }

    public static void init() {
        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());


        for (Potion potion: ModItems.POTION_EFFECTS) {
            RegistryObject<Block> cake = createCakeBlock(BuiltInRegistries.POTION.getKey(potion).getPath() + "_cake_block", potion); // CHANGE
            Data.NEW_CAKE_BLOCKS.add(cake);
            Data.CAKES_WITH_POTIONS.put(cake, potion);
        }
    }
}

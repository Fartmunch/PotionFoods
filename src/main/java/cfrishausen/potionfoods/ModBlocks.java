package cfrishausen.potionfoods;

import cfrishausen.potionfoods.data.Data;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PotionFoods.MOD_ID);


    public static RegistryObject<Block> createCakeBlock(String name, Potion potion) {
        return BLOCKS.register(name, () -> new PotionFoodCake(AbstractBlock.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.WOOL), potion));

    }

    public static void init() {
        ModBlocks.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());


        for (Potion potion: ModItems.POTION_EFFECTS) {
            RegistryObject<Block> cake = createCakeBlock(potion.getRegistryName().getPath()+"_cake_block", potion);
            Data.NEW_CAKE_BLOCKS.add(cake);
            Data.CAKES_WITH_POTIONS.put(cake, potion);
        }
    }
}

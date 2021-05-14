package cfrishausen.potionfoods;

import cfrishausen.potionfoods.data.Data;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PotionFoods.MOD_ID);


    public static RegistryObject<Block> createCakeBlock(String name) {
        return BLOCKS.register(name, () -> new Block(AbstractBlock.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.WOOL)));

//    public static RegistryObject<Block> createCakeBlock(String name) {
//        return BLOCKS.register(name, () -> {
//            return new Block(AbstractBlock.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.WOOL));
//        });

//        Data.NEW_CAKE_BLOCKS.add(cake);
    }

    public static final RegistryObject<Block> TEST = BLOCKS.register("test",
            () -> new Block(AbstractBlock.Properties.of(Material.CAKE).strength(0.5F).sound(SoundType.WOOL)));

    static {
        for (Potion potion: ModItems.POTION_EFFECTS) {
            RegistryObject<Block> cake = createCakeBlock(potion.getRegistryName().getPath()+"_cake_block");
            Data.NEW_CAKE_BLOCKS.add(cake);
            System.out.println(cake.getId() + "HEHEHEHEHEHEHEHEHEHEHEH");
        }
    }
}

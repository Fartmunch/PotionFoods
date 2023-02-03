package cfrishausen.potionfoods.data;

import cfrishausen.potionfoods.PotionFoods;
import com.google.common.collect.Maps;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.core.Direction;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

public class PotionBlockStateProvider extends BlockStateProvider {

    public PotionBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PotionFoods.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        // Iterates cakes from Potion foods and creates model and blockstate files through method.
        for (RegistryObject<Block> cakeBlock : Data.NEW_CAKE_BLOCKS) {
            Map<Integer, ModelFile> cakeMap = Maps.newHashMap();
            generateBlockstateAndModels(cakeBlock, cakeMap);

        }
    }

    //See examples for DataGeneration
    //https://github.com/MinecraftForge/MinecraftForge/blob/1.16.x/src/test/java/net/minecraftforge/debug/DataGeneratorTest.java#L325-L652

    // Creates each of the 7 cake models
    private void generateBlockstateAndModels(RegistryObject<Block> cakeBlock, Map<Integer, ModelFile> cakeMap) {

        // Make the uneaten model
        String path = cakeBlock.getId().getPath(); // CHANGE
        ModelFile cake = models().getBuilder(path)
                // mcLoc adds prefix "minecraft: for minecraft png locations"
                .texture("particle", mcLoc("block/cake_side"))
                .texture("bottom", mcLoc("block/cake_bottom"))
                .texture("top", "potionfoods:block/cake_top")
                .texture("side", mcLoc("block/cake_side"))
                //.texture("inside", mcLoc("block/cake_inner"))
                .element()
                .from(1F, 0F, 1F)
                .to(15F, 8F, 15F)
                .face(Direction.DOWN).texture("#bottom").cullface(Direction.DOWN).end()
                .face(Direction.UP).texture("#top").end()
                .face(Direction.NORTH).texture("#side").end()
                .face(Direction.SOUTH).texture("#side").end()
                .face(Direction.WEST).texture("#side").end()
                .face(Direction.EAST).texture("#side").end()
                .end();

        cakeMap.put(0, cake);

        // Make each of the partially eaten models
        for (int i = 1; i < 7; i++) {
            ModelFile cakeSliceX = models().getBuilder(path + "_slice" + i)
                    .texture("particle", mcLoc("block/cake_side"))
                    .texture("bottom", mcLoc("block/cake_bottom"))
                    .texture("top", "potionfoods:block/cake_top")
                    .texture("side", mcLoc("block/cake_side"))
                    .texture("inside", mcLoc("block/cake_inner"))
                    .element()
                    .from(1F + (i * 2F), 0F, 1F)
                    .to(15F, 8F, 15F)
                    .face(Direction.DOWN).texture("#bottom").cullface(Direction.DOWN).end()
                    .face(Direction.UP).texture("#top").end()
                    .face(Direction.NORTH).texture("#side").end()
                    .face(Direction.SOUTH).texture("#side").end()
                    .face(Direction.WEST).texture("#inside").end()
                    .face(Direction.EAST).texture("#side").end()
                    .end();

            // Adds cake models into map with key blockstate Integer.
            cakeMap.put(i, cakeSliceX);
        }

        VariantBlockStateBuilder blockStateBuilder = getVariantBuilder(cakeBlock.get());

        // Creates one blockstate file per cake by using lambda to itterate through each blockstate and creates file
        // that points to the correct model based on the blockstate.
        blockStateBuilder.forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(getModelFil(state, cakeMap))
                        .build());
    }

    // Helper funciton that returns the cake model from given blockstate.
    private ModelFile getModelFil(BlockState state, Map<Integer, ModelFile> cakeMap) {
        return cakeMap.get(state.getValue(CakeBlock.BITES));
    }
}

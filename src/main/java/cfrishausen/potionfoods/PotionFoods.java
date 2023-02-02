package cfrishausen.potionfoods;



import cfrishausen.potionfoods.event.EventHandler;
import cfrishausen.potionfoods.registry.ModBlocks;
import cfrishausen.potionfoods.registry.ModItems;
import net.minecraftforge.fml.common.Mod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(PotionFoods.MOD_ID)
public class PotionFoods {
    public static final String MOD_ID = "potionfoods";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public PotionFoods() {
        ModBlocks.init();
        ModItems.init();

        EventHandler.register();
    }
}

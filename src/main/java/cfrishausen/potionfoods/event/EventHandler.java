package cfrishausen.potionfoods.event;

import cfrishausen.potionfoods.PotionFoods;
import cfrishausen.potionfoods.registry.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.CreativeModeTabRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class EventHandler {

    public static void register() {
        IEventBus mod = FMLJavaModLoadingContext.get().getModEventBus();
        mod.addListener(EventHandler::registerCreativeTabs);
    }

    public static void registerCreativeTabs(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(PotionFoods.MOD_ID, "main"), builder -> {
            builder.icon(() -> new ItemStack(ModItems.MENU_BREAD.get()));
            builder.title(Component.translatable("item_group.potionfoods.main"));
            builder.displayItems((enabledFeatures, output, displayOperatorCreativeTab) -> {
                ModItems.addItemsToTab(output);
            });
        });
    }

}

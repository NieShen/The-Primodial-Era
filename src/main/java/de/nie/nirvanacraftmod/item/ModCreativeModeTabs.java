package de.nie.nirvanacraftmod.item;

import de.nie.nirvanacraftmod.NirvanaCraft;
import de.nie.nirvanacraftmod.block.NirvanaCraftBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {

    public static  final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NirvanaCraft.MOD_ID);

    public static  final RegistryObject<CreativeModeTab> ALEXANDRITE_ITEMS_TAB = CREATIVE_MODE_TABS.register("alexandert_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(NirvanaCraftItems.ALEXANDRITE.get()))
                    .title(Component.translatable("creativetab.nirvanacraftmod.alexandrite_items"))
                    .displayItems((itemDisplayParameters, output) ->{

                    output.accept(NirvanaCraftItems.ALEXANDRITE.get());
                    output.accept(NirvanaCraftItems.RAW_ALEXANDRITE.get());

                    })
                    .build());

    public static  final RegistryObject<CreativeModeTab> ALEXANDRITE_BLOCKS_TAB = CREATIVE_MODE_TABS.register("alexandert_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(NirvanaCraftBlocks.ALEXANDRITE_BLOCK.get()))
                    .withTabsBefore(ALEXANDRITE_ITEMS_TAB.getId())
                    .title(Component.translatable("creativetab.nirvanacraftmod.alexandrite_blocks"))
                    .displayItems((itemDisplayParameters, output) ->{

                        output.accept(NirvanaCraftBlocks.ALEXANDRITE_BLOCK.get());
                        output.accept(NirvanaCraftBlocks.ALEXANDRITE_ORE.get());
                        output.accept(NirvanaCraftBlocks.ALEXANDRITE_DEEPSLATE_ORE.get());



                    })
                    .build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

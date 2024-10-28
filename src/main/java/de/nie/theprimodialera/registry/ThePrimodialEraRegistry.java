package de.nie.theprimodialera.registry;

import de.nie.theprimodialera.ThePrimodialEra;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ThePrimodialEraRegistry {

    // Deferred Register Definitions for Creative Tabs, Items, and Blocks
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ThePrimodialEra.MOD_ID);
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ThePrimodialEra.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ThePrimodialEra.MOD_ID);


    // Creative Tabs
    // Register a creative tab for The Primodial Era items
    public static final RegistryObject<CreativeModeTab> THE_PRIMODIAL_ERA_ITEMS_TAB = CREATIVE_MODE_TABS.register("the_primodial_era_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ThePrimodialEraRegistry.ALEXANDRITE.get()))
                    .title(Component.translatable("creativetab.theprimodialera.the_primodial_era_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ThePrimodialEraRegistry.ALEXANDRITE.get());
                        output.accept(ThePrimodialEraRegistry.RAW_ALEXANDRITE.get());
                    })
                    .build());

    // Register a creative tab for The Primodial Era blocks
    public static final RegistryObject<CreativeModeTab> THE_PRIMODIAL_ERA_BLOCKS_TAB = CREATIVE_MODE_TABS.register("the_primodial_era_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ThePrimodialEraRegistry.ALEXANDRITE_BLOCK.get()))
                    .withTabsBefore(THE_PRIMODIAL_ERA_ITEMS_TAB.getId())
                    .title(Component.translatable("creativetab.theprimodialera.the_primodial_era_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ThePrimodialEraRegistry.ALEXANDRITE_BLOCK.get());
                        output.accept(ThePrimodialEraRegistry.ALEXANDRITE_ORE.get());
                        output.accept(ThePrimodialEraRegistry.ALEXANDRITE_DEEPSLATE_ORE.get());
                    })
                    .build());

    // Items
    // Register Alexandrite item
    public static final RegistryObject<Item> ALEXANDRITE = ITEMS.register("alexandrite",
            () -> new Item(new Item.Properties()));

    // Register Raw Alexandrite item
    public static final RegistryObject<Item> RAW_ALEXANDRITE = ITEMS.register("raw_alexandrite",
            () -> new Item(new Item.Properties()));

    // Blocks
    // Register Alexandrite Block
    public static final RegistryObject<Block> ALEXANDRITE_BLOCK = registerBlock("alexandrite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> ALEXANDRITE_ORE = registerBlock("alexandrite_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> ALEXANDRITE_DEEPSLATE_ORE = registerBlock("alexandrite_deepslate_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 6), BlockBehaviour.Properties.of()
                    .strength(5f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    // Helper Method to Register Blocks and their Item Form
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // Helper Method to Register Block Items
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ThePrimodialEraRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    // Register all Deferred Registers to the Event Bus
    public static void registerTheEra(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

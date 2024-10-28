package de.nie.theprimodialera;

import com.mojang.logging.LogUtils;
import de.nie.theprimodialera.command.DaoCommand;
import de.nie.theprimodialera.cultivation.daoSystem.manager.BreakthroughConditionManager;
import de.nie.theprimodialera.cultivation.daoSystem.manager.DaoProgressionManager;
import de.nie.theprimodialera.events.DaoEventListener;
import de.nie.theprimodialera.registry.DaoRegistry;
import de.nie.theprimodialera.registry.ThePrimodialEraRegistry;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ThePrimodialEra.MOD_ID)
public class ThePrimodialEra {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "theprimodialera";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    BreakthroughConditionManager conditionManager = new BreakthroughConditionManager();
    DaoProgressionManager daoProgressionManager = new DaoProgressionManager(conditionManager);

    public ThePrimodialEra() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        MinecraftForge.EVENT_BUS.register(new DaoEventListener());

        LOGGER.info("Registering The Primordial Era Registry");
        ThePrimodialEraRegistry.registerTheEra(modEventBus);

        LOGGER.info("Registering DAOs");
        DaoRegistry.registerDaos();

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        DaoRegistry.registerDaos();
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ThePrimodialEraRegistry.ALEXANDRITE);
            event.accept(ThePrimodialEraRegistry.RAW_ALEXANDRITE);
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS){
            event.accept(ThePrimodialEraRegistry.ALEXANDRITE_BLOCK);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Register the DaoCommand here
        DaoCommand.register(event.getServer().getCommands().getDispatcher());
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

       }
    }
}
package de.nie.theprimodialera.events;


import de.nie.theprimodialera.ThePrimodialEra;
import de.nie.theprimodialera.cultivation.daoSystem.Dao;
import de.nie.theprimodialera.cultivation.daoSystem.PlayerDaoProgress;
import de.nie.theprimodialera.cultivation.daoSystem.manager.DaoProgressionManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;

@Mod.EventBusSubscriber(modid = ThePrimodialEra.MOD_ID, value = Dist.CLIENT)
public class DaoEventListener {
    private static final HashMap<Integer, KeyMapping> mappings = new HashMap<>();
    private static DaoProgressionManager daoProgressionManager;

    static {
        // Initialize key mappings
        mappings.put(0, new KeyMapping("key.theprimodialera.comprehend_dao", GLFW.GLFW_KEY_K, "key.categories.cultivation"));
    }

    // Register key mappings using the new RegisterKeyMappingsEvent
    @SubscribeEvent
    public static void registerKeyBindings(RegisterKeyMappingsEvent event) {
        mappings.values().forEach(event::register);
    }

    // Set DaoProgressionManager
    public static void setDaoProgressionManager(DaoProgressionManager manager) {
        daoProgressionManager = manager;
    }

    @SubscribeEvent
    public static void onKeyPressed(InputEvent.Key event) {
        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        // Check if comprehension key was pressed and trigger comprehension
        if (mappings.get(0) != null && mappings.get(0).consumeClick()) { // Added null check
            triggerDaoComprehension(player);
        }
    }

    private static void triggerDaoComprehension(Player player) {
        if (daoProgressionManager == null) return; // Ensure the manager is set

        PlayerDaoProgress playerData = daoProgressionManager.getPlayerData(player);
        if (playerData == null) {
            playerData = new PlayerDaoProgress();
            daoProgressionManager.addPlayer(player, playerData);
        }

        Dao knownDao = playerData.getKnownDao();
        if (knownDao != null) {
            daoProgressionManager.addExperience(player, knownDao, 10);
            System.out.println("Player is now learning " + knownDao.getName());
        } else {
            System.out.println("Player does not know any Dao to comprehend.");
        }
    }
}

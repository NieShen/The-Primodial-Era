package de.nie.theprimodialera.cultivation.daoSystem.breakthrough;


import de.nie.theprimodialera.cultivation.daoSystem.Dao;
import de.nie.theprimodialera.cultivation.daoSystem.PlayerDaoProgress;
import net.minecraft.world.entity.player.Player;

@FunctionalInterface
public interface BreakthroughCondition {
    boolean isMet(PlayerDaoProgress playerData, Dao dao, Player player);
}


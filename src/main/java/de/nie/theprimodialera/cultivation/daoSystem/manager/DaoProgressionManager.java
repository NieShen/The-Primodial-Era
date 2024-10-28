package de.nie.theprimodialera.cultivation.daoSystem.manager;

import de.nie.theprimodialera.cultivation.daoSystem.Dao;
import de.nie.theprimodialera.cultivation.daoSystem.DaoStage;
import de.nie.theprimodialera.cultivation.daoSystem.PlayerDaoProgress;
import de.nie.theprimodialera.cultivation.daoSystem.breakthrough.BreakthroughCondition;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoProgressionManager {
    private final Map<Player, PlayerDaoProgress> playerDataMap;
    private final BreakthroughConditionManager conditionManager;

    public DaoProgressionManager(BreakthroughConditionManager conditionManager) {
        this.playerDataMap = new HashMap<>();
        this.conditionManager = conditionManager;
    }

    public void addPlayer(Player player, PlayerDaoProgress playerData) {
        playerDataMap.put(player, playerData);
    }

    public PlayerDaoProgress getPlayerData(Player player) {
        return playerDataMap.get(player);
    }

    public void addDaoToPlayer(Player player, Dao dao) {
        PlayerDaoProgress playerData = playerDataMap.get(player);
        if (playerData == null) return;

        // Check if player can learn the new Dao
        if (canLearnDao(playerData, dao)) {
            playerData.addDao(dao);
            applyComprehensionMalus(playerData, dao);
        } else {
            System.out.println("Player cannot learn " + dao.getName() + " due to restrictions.");
        }
    }

    private boolean canLearnDao(PlayerDaoProgress playerData, Dao newDao) {
        // Check for banned Daos
        for (Dao bannedDao : newDao.getBannedDaos()) {
            if (playerData.hasDao(bannedDao)) {
                return false; // Player cannot learn this Dao
            }
        }

        return true; // Player can learn the Dao
    }

    private void applyComprehensionMalus(PlayerDaoProgress playerData, Dao newDao) {
        // Loop through all restricted Daos to apply comprehension malus
        for (Dao restrictedDao : newDao.getRestrictedDaos()) {
            if (playerData.hasDao(restrictedDao)) {
                double malus = restrictedDao.getComprehensionMalus(restrictedDao);
                playerData.addComprehensionMalus(newDao, malus);
                System.out.println("Comprehension malus of " + malus + " applied to " + newDao.getName());
            }
        }
    }

    public void addExperience(Player player, Dao dao, int baseExperience, double... modifiers) {
        PlayerDaoProgress playerData = playerDataMap.get(player);
        if (playerData == null) return;

        int adjustedExperience = baseExperience;
        for (double modifier : modifiers) adjustedExperience *= modifier;

        playerData.addExperience(dao, adjustedExperience);

        if (playerData.getExperience(dao) >= playerData.getCurrentThreshold(dao)) {
            breakthrough(player, dao);
        }
    }

    private void breakthrough(Player player, Dao dao) {
        PlayerDaoProgress playerData = playerDataMap.get(player);
        DaoStage.Level currentLevel = playerData.getLevel(dao);

        if (currentLevel == DaoStage.Level.LVL_9) {
            if (advanceStage(player, dao)) playerData.resetDaoProgress(dao);
        } else {
            playerData.setLevel(dao, DaoStage.Level.values()[currentLevel.ordinal() + 1]);
        }
    }

    private boolean advanceStage(Player player, Dao dao) {
        PlayerDaoProgress playerData = playerDataMap.get(player);
        DaoStage currentStage = playerData.getStage(dao);

        List<BreakthroughCondition> conditions = conditionManager.getConditions(dao, currentStage);
        if (conditions != null && conditions.stream().allMatch(condition -> condition.isMet(playerData, dao, player))) {
            playerData.advanceStage(dao);
            return true;
        }
        return false;
    }


}

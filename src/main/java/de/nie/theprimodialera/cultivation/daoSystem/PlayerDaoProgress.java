package de.nie.theprimodialera.cultivation.daoSystem;

import de.nie.theprimodialera.util.enums.DaoRank;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class PlayerDaoProgress {
    private static Map<Dao, DaoData> daoProgressMap = Map.of();
    private final Map<Dao, Double> comprehensionMalusMap;

    public PlayerDaoProgress() {
        this.daoProgressMap = new HashMap<>();
        this.comprehensionMalusMap = new HashMap<>();
    }

    public Dao getKnownDao() {
        if (daoProgressMap.isEmpty()) {
            return null; // Return null if the player has no known Daos
        }
        // Return any Dao from the map (e.g., the first one)
        return daoProgressMap.keySet().iterator().next();
    }

    // Nested class to hold individual Dao progression data
    private static class DaoData {
        private int experience;
        private DaoStage.Level level;
        private DaoStage stage;
        private final DaoRank rank;

        public DaoData(DaoRank rank) {
            this.experience = 0;
            this.level = DaoStage.Level.LVL_1;
            this.stage = DaoStage.GLIMPSE;
            this.rank = rank;
        }

        // Getter and Setter methods
        public int getExperience() { return experience; }

        public void addExperience(int exp) { this.experience += exp; }

        public DaoStage.Level getLevel() { return level; }

        public void setLevel(DaoStage.Level level) { this.level = level; }

        public DaoStage getStage() { return stage; }

        public void advanceStage() {
            if (stage != DaoStage.TRANSCENDED) {
                this.stage = DaoStage.values()[stage.ordinal() + 1];
            }
        }

        public DaoRank getRank() { return rank; }

        public int getCurrentThreshold() {
            return level.getExperienceThreshold();
        }

        public void resetExperience() { this.experience = 0; }
    }

    // Add Dao to player with initial data
    public void addDao(Dao dao) {
        if (!daoProgressMap.containsKey(dao)) {
            daoProgressMap.put(dao, new DaoData(dao.getRank()));
        }
    }

    // Add experience to a specific Dao, checking for level-up
    public void addExperience(Dao dao, int experience) {
        DaoData daoData = daoProgressMap.get(dao);
        if (daoData == null) return;

        daoData.addExperience(experience);
        checkLevelUp(dao, daoData); // Check for level-up or breakthrough
    }

    private void checkLevelUp(Dao dao, DaoData daoData) {
        int threshold = daoData.getCurrentThreshold();
        while (daoData.getExperience() >= threshold) {
            daoData.addExperience(-threshold);
            daoData.setLevel(DaoStage.Level.values()[daoData.getLevel().ordinal() + 1]);
            if (daoData.getLevel() == DaoStage.Level.LVL_9) {
                daoData.advanceStage();
                daoData.setLevel(DaoStage.Level.LVL_1); // Reset level on stage advancement
            }
            threshold = daoData.getCurrentThreshold();
        }
    }

    public boolean hasItem(Player player, String itemName) {
        for (ItemStack itemStack : player.getInventory().items) {
            // Check if the item matches using the item’s registry path
            if (itemStack.getItem().getDescriptionId().equals("item." + itemName)) {
                return true; // Item found in inventory
            }
        }
        return false; // Item not found
    }

    // Get the current experience threshold for the next level
    public int getCurrentThreshold(Dao dao) {
        DaoData daoData = daoProgressMap.get(dao);
        return daoData != null ? daoData.getCurrentThreshold() : 0;
    }

    // Directly set the level for a specific Dao
    public void setLevel(Dao dao, DaoStage.Level level) {
        DaoData daoData = daoProgressMap.get(dao);
        if (daoData != null) {
            daoData.setLevel(level);
        }
    }

    // Advance the stage for a specific Dao
    public void advanceStage(Dao dao) {
        DaoData daoData = daoProgressMap.get(dao);
        if (daoData != null) {
            daoData.advanceStage();
        }
    }

    // Getters for Dao information
    public int getExperience(Dao dao) { return daoProgressMap.getOrDefault(dao, new DaoData(DaoRank.LESSER)).getExperience(); }

    public DaoStage.Level getLevel(Dao dao) { return daoProgressMap.getOrDefault(dao, new DaoData(DaoRank.LESSER)).getLevel(); }

    public DaoStage getStage(Dao dao) { return daoProgressMap.getOrDefault(dao, new DaoData(DaoRank.LESSER)).getStage(); }

    public DaoRank getRank(Dao dao) { return daoProgressMap.getOrDefault(dao, new DaoData(DaoRank.LESSER)).getRank(); }

    public void resetDaoProgress(Dao dao) {
        daoProgressMap.put(dao, new DaoData(dao.getRank()));
    }

    public boolean hasDao(Dao dao) {
        return daoProgressMap.containsKey(dao);
    }



    // Add comprehension malus for a specific Dao
    public void addComprehensionMalus(Dao dao, double malus) {
        comprehensionMalusMap.put(dao, comprehensionMalusMap.getOrDefault(dao, 0.0) + malus);
    }

    // Get comprehension malus for a specific Dao
    public double getComprehensionMalus(Dao dao) {
        return comprehensionMalusMap.getOrDefault(dao, 0.0);
    }

}

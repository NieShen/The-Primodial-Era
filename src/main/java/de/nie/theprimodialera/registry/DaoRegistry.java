    package de.nie.theprimodialera.registry;
    import de.nie.theprimodialera.cultivation.daoSystem.Dao;
    import de.nie.theprimodialera.cultivation.daoSystem.DaoStage;
    import de.nie.theprimodialera.cultivation.daoSystem.breakthrough.BreakthroughCondition;
    import de.nie.theprimodialera.util.enums.DaoType;
    import de.nie.theprimodialera.util.enums.DaoRank;

    import java.util.*;

    public class DaoRegistry {
        private static final Map<String, Dao> daos = new HashMap<>();
        private static final Map<Dao, Map<DaoStage, List<BreakthroughCondition>>> breakthroughConditionsByDao = new HashMap<>();

        public static void registerDaoWithConditions(Dao dao, DaoStage targetStage, List<BreakthroughCondition> conditions) {
            breakthroughConditionsByDao
                    .computeIfAbsent(dao, k -> new HashMap<>())
                    .put(targetStage, conditions);
        }

        public static List<BreakthroughCondition> getConditionsForDaoAndStage(Dao dao, DaoStage targetStage) {
            return breakthroughConditionsByDao
                    .getOrDefault(dao, Collections.emptyMap())
                    .getOrDefault(targetStage, Collections.emptyList());
        }

        public static void registerDaos() {
            Dao emberDao = new Dao("Ember Dao", DaoRank.LESSER, DaoType.ELEMENTAL, new ArrayList<>(), new ArrayList<>());
            Dao mistDao = new Dao("Mist Dao", DaoRank.LESSER, DaoType.ELEMENTAL, new ArrayList<>(), new ArrayList<>());
            Dao pebbleDao = new Dao("Pebble Dao", DaoRank.LESSER, DaoType.ELEMENTAL, new ArrayList<>(), new ArrayList<>());
            Dao breezeDao = new Dao("Breeze Dao", DaoRank.LESSER, DaoType.ELEMENTAL, new ArrayList<>(), new ArrayList<>());
            Dao sparkDao = new Dao("Spark Dao", DaoRank.LESSER, DaoType.ELEMENTAL, new ArrayList<>(), new ArrayList<>());
            Dao frostDao = new Dao("Frost Dao", DaoRank.LESSER, DaoType.ELEMENTAL, new ArrayList<>(), new ArrayList<>());
            Dao saplingDao = new Dao("Sapling Dao", DaoRank.LESSER, DaoType.ELEMENTAL, new ArrayList<>(), new ArrayList<>());

            Dao lesserCarnageDao = new Dao("Lesser Carnage Dao", DaoRank.LESSER, DaoType.SPECIAL, new ArrayList<>(), new ArrayList<>());
            Dao buddingSummerDao = new Dao("Budding Season Dao - Summer", DaoRank.LESSER, DaoType.SPECIAL, new ArrayList<>(), new ArrayList<>());
            Dao buddingWinterDao = new Dao("Budding Season Dao - Winter", DaoRank.LESSER, DaoType.SPECIAL, new ArrayList<>(), new ArrayList<>());
            Dao buddingAutumnDao = new Dao("Budding Season Dao - Autumn", DaoRank.LESSER, DaoType.SPECIAL, new ArrayList<>(), new ArrayList<>());
            Dao buddingSpringDao = new Dao("Budding Season Dao - Spring", DaoRank.LESSER, DaoType.SPECIAL, new ArrayList<>(), new ArrayList<>());

            registerDao(emberDao);
            registerDao(mistDao);
            registerDao(pebbleDao);
            registerDao(breezeDao);
            registerDao(sparkDao);
            registerDao(frostDao);
            registerDao(saplingDao);
            registerDao(lesserCarnageDao);
            registerDao(buddingSummerDao);
            registerDao(buddingWinterDao);
            registerDao(buddingAutumnDao);
            registerDao(buddingSpringDao);

            setDaoRelationships();

            // Register breakthrough conditions for Ember Dao
            registerDaoWithConditions(emberDao, DaoStage.INTENT, Arrays.asList(
                    (playerData, dao, player) -> playerData.getExperience(dao) >= 2000,
                    (playerData, dao, player) -> playerData.hasItem(player, "flame_essence")
            ));
            registerDaoWithConditions(emberDao, DaoStage.FIELD, Arrays.asList(
                    (playerData, dao, player) -> playerData.getExperience(dao) >= 4000,      // Needs 4000 experience
                    (playerData, dao, player) -> playerData.hasItem(player, "fire_crystal")   // Requires "Fire Crystal" item
            ));
            registerDaoWithConditions(emberDao, DaoStage.DOMAIN, Arrays.asList(
                    (playerData, dao, player) -> playerData.getExperience(dao) >= 8000,      // Needs 8000 experience
                    (playerData, dao, player) -> playerData.hasItem(player, "flame_soul")     // Requires "Flame Soul" item
            ));
            registerDaoWithConditions(emberDao, DaoStage.MASTERED, Arrays.asList(
                    (playerData, dao, player) -> playerData.getExperience(dao) >= 16000,     // Needs 16000 experience
                    (playerData, dao, player) -> playerData.hasItem(player, "ember_stone")    // Requires "Ember Stone" item
            ));

// Register breakthrough conditions for Mist Dao

            registerDaoWithConditions(mistDao, DaoStage.INTENT, Arrays.asList(
                    (playerData, dao, player) -> playerData.getExperience(dao) >= 2000,      // Needs 2000 experience
                    (playerData, dao, player) -> playerData.hasItem(player, "cloud_sapphire") // Requires "Cloud Sapphire" item
            ));
            registerDaoWithConditions(mistDao, DaoStage.FIELD, Arrays.asList(
                    (playerData, dao, player) -> playerData.getExperience(dao) >= 4000,      // Needs 4000 experience
                    (playerData, dao, player) -> playerData.hasItem(player, "misty_crystal")  // Requires "Misty Crystal" item
            ));
            registerDaoWithConditions(mistDao, DaoStage.DOMAIN, Arrays.asList(
                    (playerData, dao, player) -> playerData.getExperience(dao) >= 8000,      // Needs 8000 experience
                    (playerData, dao, player) -> playerData.hasItem(player, "foggy_gem")      // Requires "Foggy Gem" item
            ));
            registerDaoWithConditions(mistDao, DaoStage.MASTERED, Arrays.asList(
                    (playerData, dao, player) -> playerData.getExperience(dao) >= 16000,     // Needs 16000 experience
                    (playerData, dao, player) -> playerData.hasItem(player, "misty_crystal")   // Requires "Misty Stone" item
            ));

// Register breakthrough conditions for Pebble Dao

            registerDaoWithConditions(pebbleDao, DaoStage.INTENT, Arrays.asList(
                    (playerData, dao, player) -> playerData.getExperience(dao) >= 2000,      // Needs 2000 experience
                    (playerData, dao, player) -> playerData.hasItem(player, "stone_sapphire")  // Requires "Stone Sapphire" item
            ));
            registerDaoWithConditions(pebbleDao, DaoStage.FIELD, Arrays.asList(
                    (playerData, dao, player) -> playerData.getExperience(dao) >= 4000,      // Needs 4000 experience
                    (playerData, dao, player) -> playerData.hasItem(player, "earth_crystal")   // Requires "Earth Crystal" item
            ));
            registerDaoWithConditions(pebbleDao, DaoStage.DOMAIN, Arrays.asList(
                    (playerData, dao, player) -> playerData.getExperience(dao) >= 8000,      // Needs 8000 experience
                    (playerData, dao, player) -> playerData.hasItem(player, "granite_gem")     // Requires "Granite Gem" item
            ));
            registerDaoWithConditions(pebbleDao, DaoStage.MASTERED, Arrays.asList(
                    (playerData, dao, player) -> playerData.getExperience(dao) >= 16000,     // Needs 16000 experience
                    (playerData, dao, player) -> playerData.hasItem(player, "pebble_stone")    // Requires "Pebble Stone" item
            ));

// Register breakthrough conditions for Breeze Dao

            registerDaoWithConditions(breezeDao, DaoStage.INTENT, Arrays.asList(
                    (playerData, dao, player) -> playerData.getExperience(dao) >= 2000,      // Needs 2000 experience
                    (playerData, dao, player) -> playerData.hasItem(player, "wind_sapphire")  // Requires "Wind Sapphire" item
            ));
            registerDaoWithConditions(breezeDao, DaoStage.FIELD, Arrays.asList(
                    (playerData, dao, player) -> playerData.getExperience(dao) >= 4000,      // Needs 4000 experience
                    (playerData, dao, player) -> playerData.hasItem(player, "gale_crystal")    // Requires "Gale Crystal" item
            ));
            registerDaoWithConditions(breezeDao, DaoStage.DOMAIN, Arrays.asList(
                    (playerData, dao, player) -> playerData.getExperience(dao) >= 8000,      // Needs 8000 experience
                    (playerData, dao, player) -> playerData.hasItem(player, "air_gem")        // Requires "Air Gem" item
            ));
            registerDaoWithConditions(breezeDao, DaoStage.MASTERED, Arrays.asList(
                    (playerData, dao, player) -> playerData.getExperience(dao) >= 16000,     // Needs 16000 experience
                    (playerData, dao, player) -> playerData.hasItem(player, "breeze_stone")   // Requires "Breeze Stone" item
            ));

        }



        private static void registerDao(Dao dao) {
            daos.put(dao.getName(), dao);
        }

        public static Dao getDao(String name) {
            return daos.get(name);
        }

        public static Map<String, Dao> getAllDaos() {
            return daos;
        }


        private static void setDaoRelationships() {
            setRelationships(RESTRICTIONS, false);
            setRelationships(BANNED_DAOS, true);
        }

        private static final Map<String, List<String>> RESTRICTIONS = Map.ofEntries(
                Map.entry("Ember Dao", List.of("Mist Dao")),
                Map.entry("Mist Dao", List.of("Breeze Dao")),
                Map.entry("Pebble Dao", List.of("Ember Dao")),
                Map.entry("Breeze Dao", List.of("Pebble Dao"))
        );

        private static final Map<String, List<String>> BANNED_DAOS = Map.ofEntries(
                Map.entry("Ember Dao", List.of("Frost Dao")),
                Map.entry("Mist Dao", List.of("Ember Dao")),
                Map.entry("Pebble Dao", List.of("Breeze Dao")),
                Map.entry("Breeze Dao", List.of("Pebble Dao"))
        );

        private static void setRelationships(Map<String, List<String>> relationships, boolean isBanned) {
            for (Map.Entry<String, List<String>> entry : relationships.entrySet()) {
                String daoName = entry.getKey();
                for (String relatedDaoName : entry.getValue()) {
                    addRelationship(daoName, relatedDaoName, isBanned);
                }
            }
        }

        private static void addRelationship(String daoName, String relatedDaoName, boolean isBanned) {
            Dao dao = getDao(daoName);
            Dao relatedDao = getDao(relatedDaoName);

            if (dao != null && relatedDao != null) {
                if (isBanned) {
                    dao.addBannedDao(relatedDao);
                    relatedDao.addBannedDao(dao);
                } else {
                    dao.addRestrictedDao(relatedDao);
                    relatedDao.addRestrictedDao(dao);
                }
            }
        }
    }

package de.nie.theprimodialera.cultivation.daoSystem.manager;

import de.nie.theprimodialera.cultivation.daoSystem.Dao;
import de.nie.theprimodialera.cultivation.daoSystem.DaoStage;
import de.nie.theprimodialera.cultivation.daoSystem.breakthrough.BreakthroughCondition;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreakthroughConditionManager {
    private final Map<Dao, Map<DaoStage, List<BreakthroughCondition>>> conditionsMap;

    public BreakthroughConditionManager() {
        this.conditionsMap = new HashMap<>();
    }

    public void addConditions(Dao dao, DaoStage stage, List<BreakthroughCondition> conditions) {
        conditionsMap.computeIfAbsent(dao, k -> new HashMap<>()).put(stage, conditions);
    }

    public List<BreakthroughCondition> getConditions(Dao dao, DaoStage stage) {
        return conditionsMap.getOrDefault(dao, new HashMap<>()).get(stage);
    }
}

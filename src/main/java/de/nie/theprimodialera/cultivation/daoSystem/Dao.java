package de.nie.theprimodialera.cultivation.daoSystem;

import de.nie.theprimodialera.util.enums.DaoRank;
import de.nie.theprimodialera.util.enums.DaoType;

import java.util.*;

public class Dao {
    private final String name;
    private final DaoRank rank;
    private final DaoType type;
    private final List<Dao> restrictedDaos;
    private final List<Dao> bannedDaos;
    private final List<Dao> offspringDaos;
    private final Map<Dao, Double> comprehensionMalusMap;

    public Dao(String name, DaoRank rank, DaoType type, List<Dao> restrictedDaos, List<Dao> bannedDaos) {
        this.name = name;
        this.rank = rank;
        this.type = type;
        this.restrictedDaos = new ArrayList<>(restrictedDaos);
        this.bannedDaos = new ArrayList<>(bannedDaos);
        this.offspringDaos = new ArrayList<>();
        this.comprehensionMalusMap = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public DaoRank getRank() {
        return rank;
    }

    public DaoType getType() {
        return type;
    }

    // Return unmodifiable lists to prevent external modification
    public List<Dao> getRestrictedDaos() {
        return Collections.unmodifiableList(restrictedDaos);
    }

    public List<Dao> getBannedDaos() {
        return Collections.unmodifiableList(bannedDaos);
    }

    public void addRestrictedDao(Dao restrictedDao) {
        if (!restrictedDaos.contains(restrictedDao)) {
            restrictedDaos.add(restrictedDao);
        }
    }

    public void addBannedDao(Dao bannedDao) {
        if (!bannedDaos.contains(bannedDao)) {
            bannedDaos.add(bannedDao);
        }
    }

    public void setComprehensionMalus(Dao restrictedDao, double malus) {
        comprehensionMalusMap.put(restrictedDao, malus);
    }

    public double getComprehensionMalus(Dao restrictedDao) {
        return comprehensionMalusMap.getOrDefault(restrictedDao, 0.0);
    }
}

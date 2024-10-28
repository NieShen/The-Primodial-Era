package de.nie.theprimodialera.cultivation.daoSystem;

import java.util.HashMap;
import java.util.Map;

public enum DaoStage {
    GLIMPSE(1.0), INTENT(1.2), FIELD(1.5), DOMAIN(2.0), MASTERED(3.0), TRANSCENDED(5.0);

    private final double experienceMultiplier;

    DaoStage(double experienceMultiplier) {
        this.experienceMultiplier = experienceMultiplier;
    }

    public double getExperienceMultiplier() {
        return experienceMultiplier;
    }

    public enum Level {
        LVL_1(100), LVL_2(200), LVL_3(300), LVL_4(400), LVL_5(500), LVL_6(600), LVL_7(700), LVL_8(800), LVL_9(900);

        private final int experienceThreshold;

        Level(int experienceThreshold) {
            this.experienceThreshold = experienceThreshold;
        }

        public int getExperienceThreshold() {
            return experienceThreshold;
        }
    }
}

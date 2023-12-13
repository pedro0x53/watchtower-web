package com.pedro0x53.WatchTower.models.Checklist;

import java.util.List;

public class Verification {
        private final int id;
        private final String name;
        private List<Requirement> requirements;

        public Verification(int id, String name, List<Requirement> requirements) {
                this.id = id;
                this.name = name;
                this.requirements = requirements;
        }

        public int getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public List<Requirement> getRequirements() {
                return requirements;
        }

        public void setRequirements(List<Requirement> requirements) {
                this.requirements = requirements;
        }
}

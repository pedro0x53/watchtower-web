package com.pedro0x53.WatchTower.models.Checklist;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Checklist {
    private List<Verification> verifications;

    public Checklist(List<Verification> verifications) {
        this.verifications = verifications;
    }

    public List<Verification> getVerifications() {
        return verifications;
    }

    @JsonIgnore
    public List<Requirement> getTODORequirements() {
        return getRequirementsFilteredByStatus(0);
    }

    @JsonIgnore
    public List<Requirement> getDoingRequirements() {
        return getRequirementsFilteredByStatus(1);
    }

    @JsonIgnore
    public List<Requirement> getDoneRequirements() {
        return getRequirementsFilteredByStatus(2);
    }

    @JsonIgnore
    private List<Requirement> getRequirementsFilteredByStatus(int status) {
        return this.verifications
                .stream()
                .flatMap(verification -> {
                    return verification.getRequirements()
                            .stream();
                })
                .filter(requirement -> {
                    return requirement.getStatus() == status;
                })
                .collect(Collectors.toList());
    }

    public void setVerifications(List<Verification> verifications) {
        this.verifications = verifications;
    }
}

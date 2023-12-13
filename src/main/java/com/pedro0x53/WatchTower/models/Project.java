package com.pedro0x53.WatchTower.models;

import com.pedro0x53.WatchTower.helpers.VerificationLevelCoder;
import com.pedro0x53.WatchTower.models.Checklist.Checklist;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String ownerID;
    private int rawLevel;
    @Column(columnDefinition="TEXT")
    private String checklistRaw;
    @Transient private VerificationLevel verificationLevel;
    @Transient private Checklist checklist;

    @Transient private static VerificationLevelCoder coder = new VerificationLevelCoder();

    public Project() {}

    public Project(String name, int rawLevel, String checklistRaw) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.ownerID = "wt-alpha";
        this.rawLevel = rawLevel;
        this.checklistRaw = checklistRaw;

        this.verificationLevel = VerificationLevel.fromRawValue(this.rawLevel);

        if (!checklistRaw.isEmpty()) {
            this.checklist = Project.coder.decode(checklistRaw);
        }
    }

    public void setup() {
        this.verificationLevel = VerificationLevel.fromRawValue(this.rawLevel);
        this.checklist = Project.coder.decode(this.checklistRaw);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public int getRawLevel() {
        return rawLevel;
    }
    public void setRawLevel(int rawLevel) {
        this.rawLevel = rawLevel;
        this.verificationLevel = VerificationLevel.fromRawValue(this.rawLevel);
    }
    public VerificationLevel getLevel() {
        return this.verificationLevel;
    }
    public void setLevel(VerificationLevel level) {
        this.verificationLevel = verificationLevel;
        this.rawLevel = this.verificationLevel.getRawValue();
    }

    public String getRawChecklist() {
        return checklistRaw;
    }
    public void setRawChecklist(String checklist) {
        this.checklistRaw = checklist;
        this.checklist = Project.coder.decode(checklist);
    }

    public Checklist getChecklist() {
        return checklist;
    }
    public void setChecklist(Checklist checklist) {
        this.checklist = checklist;
        this.checklistRaw = Project.coder.encode(checklist);
    }
}

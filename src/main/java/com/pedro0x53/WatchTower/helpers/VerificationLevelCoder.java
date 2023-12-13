package com.pedro0x53.WatchTower.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pedro0x53.WatchTower.models.Checklist.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.pedro0x53.WatchTower.models.VerificationLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class VerificationLevelCoder {
    private final ObjectMapper mapper;

    public VerificationLevelCoder() {
        this.mapper = new ObjectMapper();
        this.mapper.writer().withDefaultPrettyPrinter();
    }

    public String encode(Checklist checklist) {
        try {
            return this.mapper.writeValueAsString(checklist);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public Checklist decode(String jsonString) {
        try {
            JsonNode node = mapper.readTree(jsonString);
            return makeChecklistFrom(node);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public Checklist getChecklistForVerificationLevel(VerificationLevel level) {
        String rawChecklist = getRawChecklistForVerificationLevel(level);
        return decode(rawChecklist);
    }

    public String getRawChecklistForVerificationLevel(VerificationLevel level) {
        return getJSONContentForVerification(level.getRawValue());
    }

    private String getJSONContentForVerification(int verification) {
        String fileName = "";
        switch (VerificationLevel.fromRawValue(verification)) {
            case L1:
                fileName = "/verificationLevels/L1.json";
                break;
            case L2:
                fileName = "/verificationLevels/L2.json";
                break;
            case L1R:
                fileName = "/verificationLevels/L1R.json";
                break;
            case L2R:
                fileName = "/verificationLevels/L2R.json";
                break;
        }

        try {
            Resource resource = new ClassPathResource(fileName);
            File file = resource.getFile();
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private Checklist makeChecklistFrom(JsonNode node) {
        JsonNode verificationsNode = node.get("verifications");
        List<Verification> verifications = new ArrayList<>();

        for (JsonNode verificationNode: verificationsNode) {
            verifications.add(makeVerificationFrom(verificationNode));
        }

        return new Checklist(verifications);
    }

    private Verification makeVerificationFrom(JsonNode node) {
        int id = node.get("id").asInt();
        String name = node.get("name").asText();
        JsonNode requirementsNode = node.get("requirements");

        List<Requirement> requirements = new ArrayList<>();

        for (JsonNode requirementNode: requirementsNode) {
            requirements.add(makeRequirementFrom(requirementNode));
        }

        return new Verification(id, name, requirements);
    }

    private Requirement makeRequirementFrom(JsonNode node) {
        int id = node.get("id").asInt();
        MASVSCategory category = MASVSCategory.fromRawValue(node.get("category").asInt());
        String description = node.get("description").asText();
        int status = node.get("status").asInt();

        JsonNode testCasesNode = node.get("testCases");
        List<TestCase> testCases = new ArrayList<>();
        for (JsonNode testCaseNode: testCasesNode) {
            testCases.add(makeTestCaseFrom(testCaseNode));
        }

        return new Requirement(id, category, testCases, description, status);
    }

    private TestCase makeTestCaseFrom(JsonNode node) {
        int id = node.get("id").asInt();
        CaseType type = CaseType.fromRawValue(node.get("type").asInt());
        String url = node.get("url").asText();
        return new TestCase(id, type, url);
    }
}

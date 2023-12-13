package com.pedro0x53.WatchTower;

import com.pedro0x53.WatchTower.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WatchTowerApplication implements CommandLineRunner {
	@Autowired
	ProjectService service;

	public static void main(String[] args) { SpringApplication.run(WatchTowerApplication.class, args); }

	@Override
	public void run(String... args) throws Exception {
//		VerificationLevelCoder coder = new VerificationLevelCoder();
//		String rawChecklistL1 = coder.getRawChecklistForVerificationLevel(VerificationLevel.L1);
//		Project p0 = new Project("Test 0", 0, rawChecklistL1);
//		service.save(p0);
//
//		String rawChecklistL1R = coder.getRawChecklistForVerificationLevel(VerificationLevel.L1R);
//		Project p1 = new Project("Test 1", 1, rawChecklistL1R);
//		service.save(p1);
//
//		String rawChecklistL2 = coder.getRawChecklistForVerificationLevel(VerificationLevel.L2);
//		Project p2 = new Project("Test 2", 2, rawChecklistL2);
//		service.save(p2);
//
//		String rawChecklistL2R = coder.getRawChecklistForVerificationLevel(VerificationLevel.L2R);
//		Project p3 = new Project("Test 3", 3, rawChecklistL2R);
//		service.save(p3);
	}
}



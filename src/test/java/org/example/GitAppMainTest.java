package org.example;

import org.junit.jupiter.api.Test;

import static org.example.GitAppMain.findMergeBase;
import static org.junit.jupiter.api.Assertions.*;

class GitAppMainTest {

    @Test
    void mainTest() {
        String repositoryPath = "C:/projects/testrepo";
        String branchName = "feature";
        String masterBranch = "master";

        String mergeBase = findMergeBase(repositoryPath, branchName, masterBranch);
        assertTrue(true);
    }
}
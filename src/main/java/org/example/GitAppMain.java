package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GitAppMain {
    public static void main(String[] args) {
        System.out.println(args[0]);
        String repositoryPath = args[0];
        String branchName = "feature";
        String masterBranch = "master";

        String mergeBase = findMergeBase(repositoryPath, branchName, masterBranch);
        if (mergeBase != null) {
            String[] changedFiles = getChangedFiles(repositoryPath, mergeBase, branchName);
            if (changedFiles != null) {
                System.out.println("Changed files in feature branch:");
                for (String file : changedFiles) {
                    System.out.println(file);
                }
            }
        }
    }

    public static String findMergeBase(String repositoryPath, String branchName, String masterBranch) {
        String mergeBase = null;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\Git\\bin\\git", "-C", repositoryPath, "merge-base", branchName, masterBranch);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            mergeBase = reader.readLine();

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return mergeBase;
    }

    public static String[] getChangedFiles(String repositoryPath, String mergeBase, String branchName) {
        String[] changedFiles = null;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\Git\\bin\\git", "-C", repositoryPath, "diff", "--name-only", mergeBase + ".." + branchName);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }

            String diffOutput = stringBuilder.toString();
            changedFiles = diffOutput.split(System.lineSeparator());

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return changedFiles;
    }
}

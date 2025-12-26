
package Diagnostic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LabTestInfo {

    private List<String> testList;

    public LabTestInfo() {
        this.testList = new ArrayList<>();
    }

    public void addTestDetails(String testDetails) {
        if (testDetails == null) return;

        int colonIndex = testDetails.indexOf(':');
        if (colonIndex == -1 || colonIndex != testDetails.lastIndexOf(':')) {
            return;
        }

        String costPart = testDetails.substring(colonIndex + 1).trim();
        try {
            Integer.parseInt(costPart);
        } catch (NumberFormatException e) {
            return;
        }

        testList.add(testDetails.trim());
    }
    public List<String> getTestsWithinGivenCostRange(int minimumCost, int maximumCost) {
        List<String> result = new ArrayList<>();

        if (minimumCost > maximumCost) {
            return result;
        }

        for (String entry : testList) {
            int colonIndex = entry.indexOf(':');
            if (colonIndex == -1) continue;

            String testName = entry.substring(0, colonIndex).trim();
            String costPart = entry.substring(colonIndex + 1).trim();

            int cost;
            try {
                cost = Integer.parseInt(costPart);
            } catch (NumberFormatException e) {
                continue;
            }

            if (cost >= minimumCost && cost <= maximumCost) {
                result.add(testName);
            }
        }

        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LabTestInfo labTestInfo = new LabTestInfo();

        System.out.println("Enter the number of lab test details to be added");
        int n;
        try {
            n = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            n = 0;
        }

        System.out.println("Enter lab test details (testName:cost)");
        for (int i = 0; i < n; i++) {
            String testDetails = sc.nextLine();
            labTestInfo.addTestDetails(testDetails);
        }

        System.out.println("Enter the minimum cost");
        int minCost;
        try {
            minCost = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            minCost = Integer.MIN_VALUE;
        }

        System.out.println("Enter the maximum cost");
        int maxCost;
        try {
            maxCost = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            maxCost = Integer.MAX_VALUE;
        }

        List<String> filtered = labTestInfo.getTestsWithinGivenCostRange(minCost, maxCost);

        if (filtered.isEmpty()) {
            System.out.println("No Tests found within the specified cost range");
        } else {
            System.out.println("Tests with cost between " + minCost + " and " + maxCost + " INR:");
            for (String name : filtered) {
                System.out.println(name);
            }
        }

        sc.close();
    }
}

package Email_Spam;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of emails");
        int n;
        try {
            n = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            n = 0;
        }

        System.out.println("Enter the spam scores");

        List<Integer> originalScores = new ArrayList<>();
        while (originalScores.size() < n && sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");
            for (String p : parts) {
                if (originalScores.size() >= n) break;
                try {
                    int val = Integer.parseInt(p);
                    originalScores.add(val);
                } catch (NumberFormatException ignored) {
                    // Ignore non-integer tokens silently
                }
            }
        }

        for (int score : originalScores) {
            if (score < 0) {
                System.out.println("Negative scores are not allowed");
                sc.close();
                return;
            }
        }
        List<Integer> modified = new ArrayList<>(n);
        for (int score : originalScores) {
            if (score >= 50) {
                modified.add(score + 10);
            } else {
                modified.add(Math.max(score - 5, 0));
            }
        }

        Map<Integer, Integer> freq = new HashMap<>();
        for (int val : modified) {
            freq.put(val, freq.getOrDefault(val, 0) + 1);
        }

        long sumUnique = 0;
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            if (e.getValue() == 1) {
                sumUnique += e.getKey();
            }
        }

        if (sumUnique == 0) {
            boolean anyUnique = false;
            for (int count : freq.values()) {
                if (count == 1) { anyUnique = true; break; }
            }
            if (!anyUnique) {
                System.out.println("No unique scores found after modification");
            } else {
                System.out.println(sumUnique);
            }
        } else {
            System.out.println(sumUnique);
        }

        sc.close();
    }
}

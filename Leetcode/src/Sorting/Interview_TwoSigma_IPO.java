package Leetcode.src.Sorting;

import java.util.*;
import java.util.stream.Collectors;

public class Interview_TwoSigma_IPO {
    public static List<Integer> getUnallottedUsers(List<List<Integer>> bids, int totalShares) {
        // Id, shares, price, timestamp
        Collections.sort(bids,
                (b1, b2) -> b1.get(2) != b2.get(2) ? b2.get(2) - b1.get(2) : b1.get(3) - b2.get(3));

        int i = 0;
        while (totalShares > 0 && i < bids.size()) {
            int groupTotalShares = 0;
            int j = i;
            while (j < bids.size() && bids.get(i).get(2) == bids.get(j).get(2)) {
                groupTotalShares += bids.get(j).get(1);
                j++;
            }
            int groupSize = j - i;
            if (totalShares < groupSize) {
                i += totalShares;
                break;
            } else {
                totalShares -= groupTotalShares;
            }
            i = j;
        }
        List<Integer> result = new ArrayList<>();
        while (i < bids.size()) {
            result.add(bids.get(i).get(0));
            i++;
        }
        Collections.sort(result);
        return result;
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int bidsLength = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> bids = new ArrayList<>();
        for (int i = 0; i < bidsLength; i++) {
            bids.add(splitWords(
                    scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList())
            );
        }
        int totalShares = Integer.parseInt(scanner.nextLine());
        scanner.close();
        List<Integer> res = getUnallottedUsers(bids, totalShares);
        System.out.println(
                res.stream().map(String::valueOf).collect(Collectors.joining(" "))
        );
    }

}

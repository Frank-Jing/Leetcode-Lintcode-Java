package Leetcode.src.Sorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Interview_TwoSigma_Friends {
    public static int maxSize;
    public static List<List<Integer>> maxGroups;

    public static int maxProductFriendsGroup(List<List<Integer>> friendPairs){
        HashMap<Integer, List<List<Integer>>> stuffList = new HashMap<>();
        int maxProduct = 0;
        maxSize = 0;
        maxGroups = new ArrayList<>();

        for(List<Integer> pair : friendPairs){
            int companyId = pair.get(2);
            if(!stuffList.containsKey(companyId)){
                stuffList.put(companyId, new ArrayList<>());
            }
            List<List<Integer>> menOfSameComp = stuffList.get(companyId);
            menOfSameComp.add(pair);
            stuffList.put(companyId, menOfSameComp);
        }

        for(Map.Entry<Integer, List<List<Integer>>> mapper : stuffList.entrySet()){
            List<List<Integer>> frdsInSameCompany = mapper.getValue();

            DJS company = new DJS(frdsInSameCompany);
        }

        for(List<Integer> maxgroup: maxGroups){
            maxgroup.sort((p1,p2)->(p2-p1));
            maxProduct = Math.max(maxProduct, maxgroup.get(0)*maxgroup.get(1));
        }

        return maxProduct;
    }


    static class DJS{
        HashMap<Integer, Integer> friendAndCenter; // parent
        HashMap<Integer, List<Integer>> friendsInGroup; // friend list and size

        public DJS(List<List<Integer>> friendPairs){

            friendAndCenter = new HashMap<>();
            friendsInGroup = new HashMap<>();
            //populate friend center and friends in same group

            for(List<Integer> pair : friendPairs){

                connectTwoFriendsGroup(pair.get(0), pair.get(1));
                System.out.print(maxSize+"\n");
            }

        }
        private int findCenterFriend(int p1){
            if(!friendAndCenter.containsKey(p1)){
                friendAndCenter.put(p1,p1);
                List<Integer> group = new ArrayList<>();
                group.add(p1);
                friendsInGroup.put(p1, group);
            }

            while(friendAndCenter.get(p1)!=p1){
                p1 = friendAndCenter.get(p1);
            }

            return p1;
        }

        private void connectTwoFriendsGroup(int p1, int p2){
            int c1 = findCenterFriend(p1);
            int c2 = findCenterFriend(p2);
            //update the common friend of c2 to c1 based on size

            int updatedCommonFriend = friendsInGroup.get(c1).size() >= friendsInGroup.get(c2).size()?c1:c2;
            int removedCommonFriend = updatedCommonFriend == c1?c2:c1;
            friendAndCenter.put(removedCommonFriend,updatedCommonFriend);
            List<Integer> friendsInSmallerGroup = friendsInGroup.get(removedCommonFriend);
            List<Integer> friendsInBiggerGroup = friendsInGroup.get(updatedCommonFriend);

            for(Integer f: friendsInSmallerGroup){
                friendsInBiggerGroup.add(f);
            }
            friendsInGroup.put(updatedCommonFriend, friendsInBiggerGroup);

            if(friendsInBiggerGroup.size() == maxSize){

                maxGroups.add(friendsInBiggerGroup);
            }
            //update max size
            else if(friendsInBiggerGroup.size() > maxSize){

                maxGroups = new ArrayList<>();
                maxGroups.add(friendsInBiggerGroup);
                maxSize = friendsInBiggerGroup.size();
            }


        }
    }


    public static void main(String[] args) {
        List<Integer> fp1 = new ArrayList<>();
        //new int[][]{{1,2,1},{2,7,1},{4,1,1},{1,4,2},{3,4,2},{5,6,3},{2,7,3}};
        fp1.add(1);
        fp1.add(2);
        fp1.add(1);
        List<Integer> fp2 = new ArrayList<>();
        //new int[][]{{1,2,1},{2,7,1},{4,1,1},{1,4,2},{3,4,2},{5,6,3},{2,7,3}};
        fp2.add(2);
        fp2.add(7);
        fp2.add(1);
        List<Integer> fp3 = new ArrayList<>();
        //new int[][]{{1,2,1},{2,7,1},{4,1,1},{1,4,2},{3,4,2},{5,6,3},{2,7,3}};
        fp3.add(4);
        fp3.add(1);
        fp3.add(1);
        List<Integer> fp4 = new ArrayList<>();
        //new int[][]{{1,2,1},{2,7,1},{4,1,1},{1,4,2},{3,4,2},{5,6,3},{2,7,3}};
        fp4.add(1);
        fp4.add(4);
        fp4.add(2);
        List<Integer> fp5 = new ArrayList<>();
        //new int[][]{{1,2,1},{2,7,1},{4,1,1},{1,4,2},{3,4,2},{5,6,3},{2,7,3}};
        fp5.add(3);
        fp5.add(4);
        fp5.add(2);
        List<List<Integer>> friends = new ArrayList<>();
        friends.add(fp1);
        friends.add(fp2);
        friends.add(fp3);
        friends.add(fp4);
        friends.add(fp5);
        System.out.print(maxProductFriendsGroup(friends));
    }
}

package uta.cse3310;

import java.util.*;


public class Leaderboard
{

    // store usernames and associated scores in an ArrayList
    public List<Map<String, Integer>> leaderboardList = new ArrayList<>();

    public void addPlayer(String username)
    {
        boolean userExists = false;

        // see if username is in ArrayList
        for (Map<String, Integer> userMap : leaderboardList)
        {
            if (userMap.containsKey(username))
            {
                userExists = true;
                break;
            }
        }

        // add username with score of 0
        if (!userExists)
        {
            Map<String, Integer> newUserMap = new HashMap<>();
            newUserMap.put(username, 0);
            leaderboardList.add(newUserMap);

            /*for(int i = 0; i < leaderboardList.size(); i++)
            {
                System.out.println(leaderboardList.get(i));
            }*/
        }
    }

    // get max scores (up to 5)
    public List<Map<String, Integer>> displayLeaderboard()
    {
        // sort the ArrayList of HashMaps in descending order
        leaderboardList.sort((a, b) -> {
            Integer scoreA = a.values().iterator().next();
            Integer scoreB = b.values().iterator().next();
            return scoreB.compareTo(scoreA);
        });

        // return max scores (up to 5)
        int endIndex = Math.min(leaderboardList.size(), 5);
        return new ArrayList<>(leaderboardList.subList(0, endIndex));
    }


    // update score for a specific user
    public void updateScore(String username)
    {
        System.out.println("Updating score for: " + username);

        for (Map<String, Integer> userMap : leaderboardList)
        {
            if (userMap.containsKey(username))
            {
                int currentScore = userMap.get(username);
                userMap.put(username, currentScore + 1);
                break;
            }
        }
    }
}




package uta.cse3310;

public class Statistics {
    // this class stores global statistics of the program
    private Long runningTime;
    private Integer totalGames;
    private Integer gamesInProgress;

    public Statistics() {
        runningTime = 0L;
        totalGames = 0;
        gamesInProgress = 0;
    }

    public Long getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Long runningTime) {
        this.runningTime = runningTime;
    }

    public Integer getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(Integer totalGames) {
        this.totalGames = totalGames;
    }

    public Integer getGamesInProgress() {
        return gamesInProgress;
    }

    public void setGamesInProgress(Integer gamesInProgress) {
        this.gamesInProgress = gamesInProgress;
    }

    // Method to increment the total number of games
    public void incrementTotalGames() {
        totalGames++;
    }

    // Method to decrement the total number of games
    public void decrementTotalGames() {
        if (totalGames > 0) {
            totalGames--;
        }
    }

    // Method to increment the number of games in progress
    public void incrementGamesInProgress() {
        gamesInProgress++;
    }

    // Method to decrement the number of games in progress
    public void decrementGamesInProgress() {
        if (gamesInProgress > 0) {
            gamesInProgress--;
        }
    }

    // Method to update the running time
    public void updateRunningTime(Long elapsedTime) {
        runningTime += elapsedTime;
    }
}

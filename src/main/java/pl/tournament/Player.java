package pl.tournament;

public class Player {

    private String playerName;
    private Integer wins;
    private Integer roundsWonSum;
    private Integer roundsLostSum;

    public Player(String playerName, Integer wins, Integer roundsWonSum, Integer roundsLostSum) {
        this.playerName = playerName;
        this.wins = wins;
        this.roundsWonSum = roundsWonSum;
        this.roundsLostSum = roundsLostSum;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getRoundsWonSum() {
        return roundsWonSum;
    }

    public Integer getRoundsLostSum() {
        return roundsLostSum;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public void setRoundsWonSum(Integer roundsWonSum) {
        this.roundsWonSum = roundsWonSum;
    }

    public void setRoundsLostSum(Integer roundsLostSum) {
        this.roundsLostSum = roundsLostSum;
    }

    @Override
    public String toString() {
        return "\nplayerName: " + playerName;
    }
}

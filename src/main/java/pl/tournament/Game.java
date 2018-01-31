package pl.tournament;

public class Game {

    private Integer gameId;
    private Player playerOne;
    private Player playerTwo;
    private Integer playerOneRoundsWon;
    private Integer playerTwoRoundsWon;

    public Game(Integer gameId, Player playerOne, Player playerTwo, Integer playerOneRoundsWon, Integer playerTwoRoundsWon) {
        this.gameId = gameId;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.playerOneRoundsWon = playerOneRoundsWon;
        this.playerTwoRoundsWon = playerTwoRoundsWon;
    }

    public Integer getGameId() {
        return gameId;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Integer getPlayerOneRoundsWon() {
        return playerOneRoundsWon;
    }

    public Integer getPlayerTwoRoundsWon() {
        return playerTwoRoundsWon;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public void setPlayerOneRoundsWon(Integer playerOneRoundsWon) {
        this.playerOneRoundsWon = playerOneRoundsWon;
    }

    public void setPlayerTwoRoundsWon(Integer playerTwoRoundsWon) {
        this.playerTwoRoundsWon = playerTwoRoundsWon;
    }

    @Override
    public String toString() {
        return "\ngameId: " + gameId +
                playerOne +
                playerTwo +
                " \nplayerOneRoundsWon " + playerOneRoundsWon +
                " playerTwoRoundsWon " + playerTwoRoundsWon;
    }
}

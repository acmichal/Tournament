package pl.tournament;

import org.apache.log4j.Logger;

import java.util.*;

public class PlayOffFormat implements Tournament {

    private Logger logger = Logger.getLogger(PlayOffFormat.class);

    private List<Player> listOfPlayers = new LinkedList<>();
    private List<Player> tempListOfPlayers = new LinkedList<>();
    private List<Player> tempListOfPlayers2 = new LinkedList<>();
    private List<String> listOfPlayersNames = new ArrayList<>();
    private Set<Game> reportedGames = new LinkedHashSet<>();

    Integer roundNumber;

    @Override
    public void addPlayer() {
        if (listOfPlayers.size() < 32) {
            logger.info("Current player list: " + listOfPlayers);
            String addedPlayerName;
            Scanner input = new Scanner(System.in);
            logger.info("Enter new player name: ");
            addedPlayerName = input.next();
            listOfPlayers.add(new Player(addedPlayerName, 0, 0, 0));
            logger.info("Player: " + addedPlayerName + " added.\n Current player list: " + listOfPlayers);
        }
        else {
            logger.info("Maximum of 32 players allowed!");
        }
    }

    @Override
    public void removePlayer() {
        logger.info("Current player list: " + listOfPlayers);
        String removedPlayerName;
        Scanner input = new Scanner(System.in);
        logger.info("Enter player to remove name: ");
        removedPlayerName = input.next();
        Iterator<Player> iterator = listOfPlayers.iterator();
        while (iterator.hasNext()) {
            Player player = iterator.next();
            if (player.getPlayerName().equals(removedPlayerName))
                iterator.remove();
        }
        logger.info("Player: " + removedPlayerName + " removed.\n Current player list: " + listOfPlayers);
    }

    @Override
    public void reportGame() {
        Game reportedGame = new Game(null, null, null, null, null);
        for (Player player : listOfPlayers) {
            listOfPlayersNames.add(player.getPlayerName());
        }
        logger.info("Games played so far: " + reportedGames);
        String firstPlayer;
        String secondPlayer;
        Integer rdsWonFirstPlayer;
        Integer rdsWonSecondPlayer;
        Scanner input = new Scanner(System.in);
        logger.info("Enter first player: ");
        firstPlayer = input.next();
        while(reportedGame.getPlayerOne() == null) {
            if (listOfPlayersNames.contains(firstPlayer)) {
                reportedGame.setPlayerOne(new Player(firstPlayer, null, null, null));
            } else {
                logger.info("This player doesn't exist!");
                logger.info("Try again: ");
                firstPlayer = input.next();
            }
        }
        logger.info("Enter second player: ");
        secondPlayer = input.next();
        while (reportedGame.getPlayerTwo() == null) {
            if (listOfPlayersNames.contains(secondPlayer)) {
                reportedGame.setPlayerTwo(new Player(secondPlayer, null, null, null));
            } else {
                logger.info("This player doesn't exist!");
                logger.info("Try again: ");
                secondPlayer = input.next();
            }
        }
        logger.info("Enter number of rounds won by first player: ");
        rdsWonFirstPlayer = input.nextInt();
        reportedGame.setPlayerOneRoundsWon(rdsWonFirstPlayer);
        logger.info("Enter number of rounds won by second player: ");
        rdsWonSecondPlayer = input.nextInt();
        reportedGame.setPlayerTwoRoundsWon(rdsWonSecondPlayer);
        reportedGame.setGameId(reportedGames.size() + 1);
        logger.info("Reported game: \n" + reportedGame);
        reportedGames.add(reportedGame);
        logger.info("All reported games: \n" + reportedGames);
    }

    @Override
    public void removeReportedGame() {
        Integer idToDelete;
        logger.info("Games played so far: " + reportedGames);
        logger.info("Enter id of a game to delete: ");
        Scanner input = new Scanner(System.in);
        idToDelete = input.nextInt();
        Iterator<Game> iterator = reportedGames.iterator();
        while (iterator.hasNext()) {
            Game reportedGame = iterator.next();
            if (reportedGame.getGameId().equals(idToDelete))
                iterator.remove();
        }
        logger.info("List of games after deletion: " + reportedGames);
    }

    private void fillLadder(List<Player> tempList) {

        Integer missingPlayersForLadder;
        for (Player player : listOfPlayers) {
            tempList.add(player);
        }
        if (tempList.size() < 2) {}
        if (tempList.size() < 4) {
            missingPlayersForLadder = 4 - tempList.size();
        }
        else if (tempList.size() < 8) {
            missingPlayersForLadder = 8 - tempList.size();
        }
        else if (tempList.size() < 16){
            missingPlayersForLadder = 16 - tempList.size();
        }
        else {
            missingPlayersForLadder = 32 - tempList.size();
        }
        for (int i=0; i < missingPlayersForLadder; i++) {
            tempList.add(new Player("next round!", 0, 0,0));
        }
    }

    private void draw(List<Player> tempList, List<Player> tempList2) {
        Random rand = new Random();
        Player randomPlayer1;
        Player randomPlayer2;
        Integer loopLength = tempList.size()/2;
        for (int i=0; i < loopLength; i++) {
            randomPlayer1 = tempList.get(rand.nextInt(tempList.size()));
            randomPlayer2 = tempList.get(rand.nextInt(tempList.size()));
            if (randomPlayer1.equals(randomPlayer2)) {
                tempList.remove(randomPlayer2);
                randomPlayer2 = tempList.get(rand.nextInt(tempList.size()));
            }
            System.out.println(randomPlayer1.getPlayerName() + " vs " + randomPlayer2.getPlayerName());
            if (randomPlayer1.getPlayerName().equals("next round!") && randomPlayer2.getPlayerName().equals("next round!")) {
                randomPlayer1.setWins(randomPlayer2.getWins() + 1);
            }
            else if (randomPlayer1.getPlayerName().equals("next round!")) {
                randomPlayer2.setWins(randomPlayer2.getWins() + 1);
            }
            else if (randomPlayer2.getPlayerName().equals("next round!")) {
                randomPlayer1.setWins(randomPlayer2.getWins() + 1);
            }
            tempList2.add(randomPlayer1);
            tempList.remove(randomPlayer1);
            tempList2.add(randomPlayer2);
            tempList.remove(randomPlayer2);
        }
    }

    private void sumWinsForEveryPlayer(List<Player> PlayerList, Set<Game> GameList) {
        for (Player player : PlayerList) {
            for (Game game : GameList) {
                if (player.getPlayerName().equals(game.getPlayerOne().getPlayerName())) {
                    if (game.getPlayerOneRoundsWon() > game.getPlayerTwoRoundsWon()) {
                        player.setWins(player.getWins() + 1);
                    }
                } else if (player.getPlayerName().equals(game.getPlayerTwo().getPlayerName())) {
                    if (game.getPlayerOneRoundsWon() < game.getPlayerTwoRoundsWon()) {
                        player.setWins(player.getWins() + 1);
                    }
                }
            }
        }
    }

    @Override
    public void showProgress() {

        if (reportedGames.size() == 0) {
            roundNumber = 1;
            fillLadder(tempListOfPlayers);
        }
        System.out.println("przed draw: " + tempListOfPlayers2);
        draw(tempListOfPlayers, tempListOfPlayers2);
        if (tempListOfPlayers2.size() > 1) {
            sumWinsForEveryPlayer(tempListOfPlayers2, reportedGames);
            Iterator<Player> iterator = tempListOfPlayers2.iterator();
            while (iterator.hasNext()) {
                Player player = iterator.next();
                if (player.getWins() < roundNumber && reportedGames.size() > 0)
                    iterator.remove();
            }
        }
        if (reportedGames.size() > 0) {
            roundNumber ++;
        }
        System.out.println("po showProgress: " + tempListOfPlayers2);
    }

    @Override
    public void saveProgress() {

    }

    @Override
    public void loadProgress() {

    }
}

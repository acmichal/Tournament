package pl.tournament;

import org.apache.log4j.Logger;

import java.util.*;

public class LeagueFormat implements Tournament {

    private Logger logger = Logger.getLogger(LeagueFormat.class);

    private List<Player> listOfPlayers = new LinkedList<>();
    private List<String> listOfPlayersNames = new ArrayList<>();
    private Set<Game> reportedGames = new LinkedHashSet<>();

    @Override
    public void addPlayer() {
        logger.info("Current player list: " + listOfPlayers);
        String addedPlayerName;
        Scanner input = new Scanner(System.in);
        logger.info("Enter new player name: ");
        addedPlayerName = input.next();
        listOfPlayers.add(new Player(addedPlayerName, 0, 0, 0));
        logger.info("Player: " + addedPlayerName + " added.\n Current player list: " + listOfPlayers);
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

    @Override
    public void showProgress() {

        List<Player> tempListOfPlayers = new LinkedList<>();
        for (Player player : listOfPlayers) {
            tempListOfPlayers.add(new Player(player.getPlayerName(),0,0,0));
        }

        for (Player player : tempListOfPlayers) {
            for (Game reportedGame : reportedGames) {
                if (player.getPlayerName().equals(reportedGame.getPlayerOne().getPlayerName())) {
                    player.setRoundsWonSum(player.getRoundsWonSum() + reportedGame.getPlayerOneRoundsWon());
                    player.setRoundsLostSum(player.getRoundsLostSum() + reportedGame.getPlayerTwoRoundsWon());
                    if (reportedGame.getPlayerOneRoundsWon() > reportedGame.getPlayerTwoRoundsWon()) {
                        player.setWins(player.getWins() + 1);
                    }
                } else if (player.getPlayerName().equals(reportedGame.getPlayerTwo().getPlayerName())) {
                    player.setRoundsWonSum(player.getRoundsWonSum() + reportedGame.getPlayerTwoRoundsWon());
                    player.setRoundsLostSum(player.getRoundsLostSum() + reportedGame.getPlayerOneRoundsWon());
                    if (reportedGame.getPlayerOneRoundsWon() < reportedGame.getPlayerTwoRoundsWon()) {
                        player.setWins(player.getWins() + 1);
                    }
                }
            }
        }

        class ResultsComparator implements Comparator<Player> {

            @Override
            public int compare(Player player1, Player player2) {
                Integer wins = player2.getWins().compareTo(player1.getWins());
                if (wins == 0) {
                    int rounds1 = player1.getRoundsWonSum() - player1.getRoundsLostSum();
                    int rounds2 = player2.getRoundsWonSum() - player2.getRoundsLostSum();
                    return Integer.compare(rounds2,rounds1);
                } else {
                    return wins;
                }
            }
        }
        tempListOfPlayers.sort(new ResultsComparator());
        for (Player player : tempListOfPlayers) {
            System.out.println("playerName: " + player.getPlayerName() + ", wins: " + player.getWins() + ", rds won: " + player.getRoundsWonSum() + ", rds lost: " + player.getRoundsLostSum());
        }
    }

    @Override
    public void saveProgress() {

    }

    @Override
    public void loadProgress() {

    }
}

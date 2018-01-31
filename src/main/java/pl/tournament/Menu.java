package pl.tournament;

import java.util.Scanner;

public class Menu {

    public void executeTournament(Tournament tournament) {

        Scanner input = new Scanner(System.in);
        System.out.println("Press 1 to add a player, \n2 to remove player, \n3 to report a game, " +
                "\n4 to delete reported game, \n5 to show progress, \n6 to abort: ");
        String chooseAction = input.next();
        Boolean running = true;
        while (running) {
            switch(chooseAction)

            {
                case "1":
                    tournament.addPlayer();
                    System.out.println("Press 1 to add a player, \n2 to remove player, \n3 to report a game, " +
                            "\n4 to delete reported game, \n5 to show progress, \n6 to abort: ");
                    chooseAction = input.next();
                    break;
                case "2":
                    tournament.removePlayer();
                    System.out.println("Press 1 to add a player, \n2 to remove player, \n3 to report a game, " +
                            "\n4 to delete reported game, \n5 to show progress, \n6 to abort: ");
                    chooseAction = input.next();
                    break;
                case "3":
                    tournament.reportGame();
                    System.out.println("Press 1 to add a player, \n2 to remove player, \n3 to report a game, " +
                            "\n4 to delete reported game, \n5 to show progress, \n6 to abort: ");
                    chooseAction = input.next();
                    break;
                case "4":
                    tournament.removeReportedGame();
                    System.out.println("Press 1 to add a player, \n2 to remove player, \n3 to report a game, " +
                            "\n4 to delete reported game, \n5 to show progress, \n6 to abort: ");
                    chooseAction = input.next();
                    break;
                case "5":
                    tournament.showProgress();
                    System.out.println("Press 1 to add a player, \n2 to remove player, \n3 to report a game, " +
                            "\n4 to delete reported game, \n5 to show progress, \n6 to abort: ");
                    chooseAction = input.next();
                    break;
                case "6":
                    System.out.println("Tournament aborted.");
                    running = false;
                    break;
                default:
                    System.out.println("Wrong input.");
                    System.out.println("Press 1 to add a player, \n2 to remove player, \n3 to report a game, " +
                            "\n4 to delete reported game, \n5 to show progress, \n6 to abort: ");
                    chooseAction = input.next();
                    break;
            }
        }
    }

    public void executeLeagueFormat() {
        executeTournament(new LeagueFormat());
    }

    public void executePlayOffFormat() {
        executeTournament(new PlayOffFormat());
    }
}

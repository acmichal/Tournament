package pl.tournament;

public interface Tournament {

    void addPlayer();

    void removePlayer();

    void reportGame();

    void removeReportedGame();

    void showProgress();

    void saveProgress();

    void loadProgress();

}

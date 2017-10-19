package io.volkan.entities;

public interface Game {
    Team getHomeTeam();
    Team getAwayTeam();
    void setHomeTeam(Team team);
    void setAwayTeam(Team team);
    void startGame();
    void endGame();
    String playGame();
}

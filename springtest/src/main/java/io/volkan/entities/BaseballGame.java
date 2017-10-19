package io.volkan.entities;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

public class BaseballGame implements Game {
    private Team homeTeam;
    private Team awayTeam;
    private DataSource dataSource;

    public BaseballGame() {}

    // Constructor injection.
    public BaseballGame(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    @Override
    @PostConstruct
    public void startGame() {
        System.out.println("Playing national anthem.");
    }

    @Override
    @PreDestroy
    public void endGame() {
        System.out.println("Thanks guys; good game.");
    }

    // Setter injection.
    // Do not forget to call the setter method when constructing the bean;
    // this setter does not auto-call itself.
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    @Override
    public Team getHomeTeam() {
        return homeTeam;
    }

    @Override
    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Override
    public Team getAwayTeam() {
        return awayTeam;
    }

    @Override
    public String playGame() {
        return Math.random() < 0.5 ? getHomeTeam().getName() : getAwayTeam().getName();
    }

    @Override
    public String toString() {
        return String.format("Game between %s and %s", awayTeam.getName(), homeTeam.getName());
    }
}

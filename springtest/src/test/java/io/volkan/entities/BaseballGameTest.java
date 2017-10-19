package io.volkan.entities;

import io.volkan.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertTrue;

// This is an integration test because it involves Spring framework and the app context.

// Repository === DAO layer.

@RunWith(SpringJUnit4ClassRunner.class) // fire up ApplicationContext and cache it.
@ContextConfiguration(classes = AppConfig.class)
public class BaseballGameTest {

    @Autowired
    private Game game;

    @Autowired
    private ApplicationContext ctx;

//    @Before
//    public void setUp() {
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
//        game = ctx.getBean("game", Game.class);
//    }

    @Test
    public void testPlayGame() throws Exception {
        String home = game.getHomeTeam().getName();
        String away = game.getAwayTeam().getName();

        String result = game.playGame();

        assertTrue(result.contains(home) || result.contains(away));
    }
}

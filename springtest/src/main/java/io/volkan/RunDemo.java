package io.volkan;

import io.volkan.entities.Game;
import io.volkan.entities.Team;
// import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Game game = context.getBean("game", Game.class);
        Team royals = context.getBean("royals", Team.class);
        Team redSox = context.getBean("redSox", Team.class);
        Team cubs = context.getBean("cubs", Team.class);

        game.setHomeTeam(royals);
        game.setAwayTeam(redSox);
        System.out.print(game.playGame());

        game.setHomeTeam(royals);
        game.setAwayTeam(cubs);
        System.out.print(game.playGame());

        context.close();

//        // Fire up the application context and have it read the metadata and therefore
//        // know what to provide for us “on demand”.
//
//        // Spring expects you to code in an abstract way as much as possible, using interfaces.
//        // And then you tell it through metadata, which classes you actually want to use.
//        // And then you use those classes in practice through their interfaces.
//
//        // Application context hosts all the beans and provides them on demand.
//        // ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//                                      // ^ It is an ApplicationContext that is using an annotation config file.
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//
//        // Hey context, give me the bean called “game”; it is an instance of a class that
//        // implements the `Game` interface.
//
//        Team royals = context.getBean("royals", Team.class);
//
//        Game game1 = context.getBean("game", Game.class);
//        System.out.println(game1);
//
//        Game game2 = context.getBean("game", Game.class);
//        game2.setAwayTeam(royals);
//
//        System.out.println(game2);
//
//        System.out.println(game1);
//
//        // By default all spring-managed beans are singletons; you get a reference to the same instance.
//
//        // System.out.println(game.playGame());
//        // System.out.println(game.playGame());
//
//        System.out.println(String.format("There are %d beans!", context.getBeanDefinitionCount()));
//
//        for (String name : context.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }
//
//        // There are 12 beans
//        // org.springframework.context.annotation.internalConfigurationAnnotationProcessor
//        // org.springframework.context.annotation.internalAutowiredAnnotationProcessor
//        // org.springframework.context.annotation.internalRequiredAnnotationProcessor
//        // org.springframework.context.annotation.internalCommonAnnotationProcessor
//        // org.springframework.context.event.internalEventListenerProcessor
//        // org.springframework.context.event.internalEventListenerFactory
//        // appConfig <- The config file itself is also a bean.
//        // org.springframework.context.annotation.ConfigurationClassPostProcessor.importAwareProcessor
//        // org.springframework.context.annotation.ConfigurationClassPostProcessor.enhancedConfigurationProcessor
//        // game
//        // cubs
//        // redSox
//
//        // Transactions
//        // Security
//        // Resource pooling
//
//        // Code tangling: (1) performing business logic and (2) writing stuff to a log file == (1), (2) are cohesively related.
//        // Code scattering: A lot of repetition; i.e., logging the same things over and over again everywhere.
//
//        // AOP
//        //      Aspect: What to do + Where to do it.
//        //      Join Point: All possible locations that we can apply our aspects. -> public methods in Spring-managed beans.
//        //      Pointcut: The actual join points that we have actually declared (defining where you want to apply the functionality).
//        //      Advice: The functionality that we want to apply. (beforeMethodCall, afterMethodCall, afterException etc)
//        //      Aspect: combines a pointcut and an advice.
//        //      Weaving: The process of applying an aspect to our system.
//
//        context.close(); // <- Pre-Destroy is called only if the bean is a singleton bean (the default).
    }
}

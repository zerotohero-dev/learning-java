package io.volkan;

import io.volkan.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;

// ACID
// Atomic, Consistent, Isolated, Durable
// All or nothing.
//         DB Integrity constraints never violated.
//                     How transactions see work donw by others.
//                               Committed changes that are permanent.

// @Transactional: Manages a transaction manager
// Both the entry and also the exit of a message queue is transactional.

@Configuration
// @Import(InfrastructureConfig.class)
@ComponentScan(basePackages = "io.volkan") // <- This is only done at startup time,
                                           //    but you’d want to reduce its scope as much as you can.
@EnableAspectJAutoProxy
public class AppConfig {

    @Autowired // <- go find a bean of type DataSource exists and assign it to this.
    private DataSource dataSource; // Spring does not care where it came from and says “go find it”.

    // @Autowired means “autowire by type” first.
    // This works, if there is exactly one bean of that type (class) available.

    // With this Java-based configuration approach, you can keep the configuration
    // of your beans centralized. Plus, you are able to use the compiler to make sure
    // that you are doing the things appropriately. And finally you have the entire
    // power of the language available to you should you want to configure the beans
    // in a more convoluted way.

    // src/main/resources -> non-java-related stuff.

    // Around aspects are pretty useful.
    // Leave aspects for the infrastructure; don’t get too clever!

    @Autowired @Qualifier("redSox")
    private Team home;

    @Resource // <- Autowire by name.
    private Team cubs;

    // @ComponentScan
    // @Autowire
    // @Qualifier
    // @Resource -> Allowed us to remove beans entirely from the configuration file,
    //              to pick them up later during the initialization process.
    //
    // Also @Autowired allows us to pick the bean from any resource we want,
    // including XML configurations.

    // What often happens is, you make a package for each type you want to be
    // found on a component scan.

    //@Bean(initMethod = "startGame", destroyMethod = "endGame")
    @Bean
    public Game game() {
        // Spring will find a bean that matches the type `DataSource` and populate the parameter
        // automatically.
        // @Scope("prototype") // <- every time I call getBean create a new one.

    // public Game game(DataSource dataSource) {
    //                         // ^ This will be picked up from the other configuration file.

        // Spring assumes that all of these classes are in fact singletons and we
        // are going to only get one.
        // So if the bean has already been instantiated, Spring will provide that
        // to you; and if it has not been instantiated, it will instantiate and
        // will put it into the Spring context.
        // BaseballGame baseballGame = new BaseballGame(redSox(), cubs());

        BaseballGame baseballGame = new BaseballGame(home, cubs);

        baseballGame.setDataSource(dataSource);

        return baseballGame;
    }

// Abstract class :: cannot be instantiated.

// Spring is a giant-ass bean factory on steroids.

// Default scope is singleton, others are request, session (if you are using SpringMVC).
// Plus you can define your custom scope for advanced use cases.

//    @Bean
//    public Team redSox() {
//        return new RedSox();
//    }

//    Spring overrides this method to return a singleton (in default config).
//    @Bean
//    public Team cubs() {
//        return new Cubs();
//    }
}

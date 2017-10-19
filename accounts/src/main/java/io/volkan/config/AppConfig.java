package io.volkan.config;

import javafx.application.Platform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "io.volkan")
@PropertySource("classpath:prod.properties")
@EnableTransactionManagement
public class AppConfig {

    // Transaction isolation levels:
    // DEFAULT
    // READ_COMMITTED    1 -> pick this in practice
    // READ_UNCOMMITTED  1 -> fastest
    // REPEATABLE_READ   3 -> row lock
    // SERIALIZABLE      4 -> table lock

    // Propagation Levels:
    //
    // MANDATORY     -> If there is a transaction, we join. If there is no transaction coming in, throw a
    //                  TransactionRequiredException.
    //
    // NEVER         -> If there is a transaction coming in then throw an exception.
    //
    // NESTED        -> RTFM. — You don’t see this all that often.
    //
    // NOT_SUPPORTED -> If there is a transaction coming in, we suspend it, we run outside the transaction,
    //                  when we are done, we resume the transaction.
    //
    // REQUIRED      -> occurs most in practice. — if a transaction is going on when the method
    //                  that we are managing gets called, we join it.
    //                  if there is no transaction in the caller, we create our own and run it.
    //
    // REQUIRES_NEW  -> If transaction tx1 is going, we suspend tx1, we create and run tx2.
    //                  When everything is done, resume tx1 regardless of what happened in tx2.
    //                  If no transaction, then create and run your own.
    //
    // SUPPORTS       -> If there is a transaction running, go ahead and join.
    //                   If no transactions running, do nothing.
    //                   i.e. We don’t really need a transaction for our method, but we are not
    //                   going to force the container put the transaction on hold just to run our method.
    //                   We’ll just go along for the ride.
    //

    @Autowired
    private Environment env;

    @Bean(name = "dataSource", destroyMethod = "shutdown")
    @Profile("test")
    public DataSource dataSourceForTest() {
        return new EmbeddedDatabaseBuilder() // <- generates a database for us on demand.
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2) // <- pure java file or in-memory databases.
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("schema.sql")
                .addScripts("data.sql")
                .build();
    }

    @Bean(name = "transactionManager")
    @Profile("test")
    public PlatformTransactionManager transactionManagerForTest() {
        return new DataSourceTransactionManager(dataSourceForTest());
    }

    @Bean(name = "dataSource")
    @Profile("prod")
    public DataSource dataSourceForProd() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.user"));
        dataSource.setPassword(env.getProperty("db.pass"));

        return dataSource;
    }

    @Bean(name = "transactionManager")
    @Profile("prod")
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSourceForProd());
    }
}

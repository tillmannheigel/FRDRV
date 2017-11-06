package de.freedriver.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
class Config {

    /*
    * Use the standard Mongo driver API to create a com.mongodb.MongoClient instance.
    */
    @Bean
    public MongoClient mongoClient() {
        return new MongoClient("localhost");
    }

}

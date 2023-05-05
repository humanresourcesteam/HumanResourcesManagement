package com.bilgeadam.config;

import com.mongodb.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@Configuration
public class MongoConfig {
    @Bean
    public GridFsTemplate gridFsTemplate(MongoClient mongoClient, MongoDatabaseFactory mongoDbFactory) throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, mongoDbFactory.getMongoDatabase().getName());
        return new GridFsTemplate(mongoDbFactory, mongoTemplate.getConverter());
    }
}

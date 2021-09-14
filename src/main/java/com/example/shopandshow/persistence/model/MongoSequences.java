package com.example.shopandshow.persistence.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "mongo_sequences")
public class MongoSequences {

    @Id
    private String id;

    private Long seq;
}

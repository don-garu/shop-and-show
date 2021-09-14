package com.example.shopandshow.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import com.example.shopandshow.persistence.model.MongoSequences;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SequenceGenerateService {

    private final MongoOperations mongoOperations;

    public long generateSequence(String seqName) {
        MongoSequences counter = mongoOperations.findAndModify(query(where("id").is(seqName)),
            new Update().inc("seq", 1), options().returnNew(true).upsert(true),
            MongoSequences.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}

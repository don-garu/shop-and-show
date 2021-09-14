package com.example.shopandshow.service;

import com.example.shopandshow.persistence.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostListener extends AbstractMongoEventListener<Post> {

    private final SequenceGenerateService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Post> event) {
        event.getSource().setId((int) sequenceGenerator.generateSequence(Post.SEQUENCE_NAME));
    }
}

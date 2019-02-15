package com.blaazha.osyc_blazek.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

@Getter
public class Post {

    private final Long id;

    private final String content;

    private final Date createdAt;

    @JsonCreator
    public Post(
            @JsonProperty("id") Long id,
            @JsonProperty("content") String content,
            @JsonProperty("createdAt") Date createdAt) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
    }
}

package com.blaazha.osyc_blazek.controller;

import com.blaazha.osyc_blazek.entity.Post;
import com.blaazha.osyc_blazek.repository.PostRepository;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/post-app/")
public class PostController {

    private final static Logger log = LoggerFactory.getLogger(PostController.class);

    private static class PostCreateRequest {

        @Getter
        private final String content;

        @JsonCreator
        public PostCreateRequest(@JsonProperty("content") String content) {
            this.content = content;
        }
    }

    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("posts")
    public List<Post> listPosts() {
        log.info("---> GET request /post-app/posts <---");
        return postRepository.listPosts();
    }

    @PostMapping("post")
    public ResponseEntity<Void> createPost(@RequestBody PostCreateRequest postCreateRequest) {
        log.info("---> POST request /post-app/post <---");
        this.postRepository.createPost(postCreateRequest.getContent());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

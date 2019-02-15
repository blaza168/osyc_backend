package com.blaazha.osyc_blazek.repository;

import com.blaazha.osyc_blazek.entity.Post;

import java.util.List;

public interface PostRepository {

    void createPost(String content);

    List<Post> listPosts();

}

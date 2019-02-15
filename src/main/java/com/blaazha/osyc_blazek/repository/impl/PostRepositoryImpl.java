package com.blaazha.osyc_blazek.repository.impl;

import com.blaazha.osyc_blazek.entity.Post;
import com.blaazha.osyc_blazek.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.util.List;

public class PostRepositoryImpl implements PostRepository {

    private static final Logger log = LoggerFactory.getLogger(PostRepositoryImpl.class);

    private static final RowMapper<Post> POST_ROW_MAPPER = (ResultSet rs, int rowNum) -> {
      return new Post(
              rs.getLong("id"),
              rs.getString("content"),
              rs.getDate("created_at"));
    };

    private final JdbcTemplate jdbcTemplate;

    public PostRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createPost(String content) {
        jdbcTemplate.update("INSERT INTO posts (content) VALUES (?)", content);
        log.info("---> Post has been successfully created <---");
    }

    @Override
    public List<Post> listPosts() {
        return jdbcTemplate.query("SELECT * FROM posts", new Object[] {}, POST_ROW_MAPPER);
    }
}

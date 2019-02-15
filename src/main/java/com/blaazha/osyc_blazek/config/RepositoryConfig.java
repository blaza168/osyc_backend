package com.blaazha.osyc_blazek.config;

import com.blaazha.osyc_blazek.repository.PostRepository;
import com.blaazha.osyc_blazek.repository.impl.PostRepositoryImpl;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

@Configuration
public class RepositoryConfig {

    private static final Logger log = LoggerFactory.getLogger(RepositoryConfig.class);

    private static final String URL_SUBFIX = "?useUnicode=true&amp;characterEncoding=utf8";

    @Bean
    public PostRepository postRepository(JdbcTemplate jdbcTemplate) {
        return new PostRepositoryImpl(jdbcTemplate);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("coreDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("coreDataSource")
    public DataSource dataSource(
            @Value("${db.url}") String url,
            @Value("${db.username}") String username,
            @Value("${db.password:}") String password,
            @Value("${db.pool.size:20}") int poolSize,
            @Value("${db.pool.evictable_idle:30000}") long minEvictable,
            @Value("${db.pool.max_lifetime:300000}") long maxLife
    ) {
        BasicDataSource ds = new BasicDataSource();

        log.info("Initializing database connection: {} as user {}", url, username);

        ds.setDriverClassLoader(com.mysql.jdbc.Driver.class.getClassLoader());
        ds.setUrl(url + URL_SUBFIX);
        ds.setUsername(username);
        ds.setPassword(password);

        ds.setTestOnBorrow(true);
        ds.setTestWhileIdle(true);
        ds.setNumTestsPerEvictionRun(poolSize);
        ds.setTimeBetweenEvictionRunsMillis(1000);
        ds.setMinEvictableIdleTimeMillis(minEvictable);
        ds.setMaxConnLifetimeMillis(maxLife);
        ds.setMinIdle(0);
        ds.setMaxIdle(poolSize);
        ds.setMaxTotal(poolSize);
        ds.setLogExpiredConnections(false);

        // migration
//		Flyway flyway = new Flyway();
//		flyway.setDataSource(ds);
//		flyway.migrate();

        return ds;
    }
}

package microblogger.db.jdbc;

import microblogger.db.Blog;
import microblogger.db.BlogNotFoundException;
import microblogger.db.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcBlogRepository implements BlogRepository {
    private final static String SELECT_BLOGS = "select bl.id, bl.message, bl.created_at from blog bl";
    private final static String SELECT_BLOG_BY_ID = SELECT_BLOGS + " and bl.id = ?";
    private final static String SELECT_RECENT_BLOGS = SELECT_BLOGS + " order by bl.created_at desc limit ?";
    private final static String SELECT_RECENT_BLOGS_BEFORE_ID = SELECT_BLOGS + " and bl.id < ? order by bl.created_at desc limit ?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcBlogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Blog> findRecent() {
        return findRecent(10);
    }

    public List<Blog> findRecent(int count) {
        return jdbcTemplate.query(SELECT_RECENT_BLOGS, new BlogRowMapper(), count);
    }

    public Blog findOne(long id){
        try {
            return jdbcTemplate.queryForObject(SELECT_BLOG_BY_ID, new BlogRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            throw new BlogNotFoundException(id);
        }

    }

    public Blog save(Blog blog) {
        long blogId = insertBlogAndReturnId(blog);
        return new Blog(blogId, blog.getMessage(), blog.getCreateTime());
    }

    public List<Blog> findBlogs(long max, int count) {
        return jdbcTemplate.query(SELECT_RECENT_BLOGS_BEFORE_ID, new BlogRowMapper(), max, count);
    }

    private long insertBlogAndReturnId(Blog blog) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("blog");
        jdbcInsert.setGeneratedKeyName("id");
        Map<String, Object> args = new HashMap<>();
        args.put("message", blog.getMessage());
        args.put("created_at", blog.getCreateTime());
        long blogId = jdbcInsert.executeAndReturnKey(args).longValue();
        return blogId;
    }

    private static class BlogRowMapper implements RowMapper<Blog> {
        public Blog mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String message = rs.getString("message");
            Date createDate = rs.getDate("created_at");
            return new Blog(id, message, createDate);
        }
    }
}

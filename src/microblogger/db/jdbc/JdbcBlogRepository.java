package microblogger.db.jdbc;

import microblogger.db.Blog;
import microblogger.db.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcBlogRepository implements BlogRepository {
    private final static String SELECT_BLOGS = "select bl.id, bl.message, bl.created_at from blog bl";
    private final static String SELECT_BLOG_BY_ID = SELECT_BLOGS + " where bl.id = ?";
    private final static String SELECT_RECENT_BLOGS = SELECT_BLOGS + " order by bl.created_at desc limit ?";
    private final static String SELECT_RECENT_BLOGS_BEFORE_ID = SELECT_BLOGS + " where bl.id < ? order by bl.created_at desc limit ?";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcBlogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Blog> findRecent() {
        return jdbcTemplate.query(SELECT_RECENT_BLOGS, new BlogRowMapper(), 20);
    }
    
    public List<Blog> findBlogs(long max, int count) {
        return jdbcTemplate.query(SELECT_RECENT_BLOGS_BEFORE_ID, new BlogRowMapper(), max, count);
    }

    public Blog findOne(long id){
        return jdbcTemplate.queryForObject(SELECT_BLOG_BY_ID, new BlogRowMapper(), id);
    }

    public void save(Blog blog) {
    	jdbcTemplate.update(
    	        "insert into blog (message, created_at)" + 
    	        "values (?, ?)",
    	        blog.getMessage(),
    	        blog.getCreateTime());
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

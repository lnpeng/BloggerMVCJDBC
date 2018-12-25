package microblogger.db.jdbc;

import microblogger.db.Blogger;
import microblogger.db.BloggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcBloggerRepository implements BloggerRepository {
    private final static String UPDATE_BLOGGER = "update blogger set username=?, password=?, first_name=?, last_name=?, email=? where id=?";
    private final static String SELECT_BLOGGER = "select id, username, password, first_name, last_name, email from Blogger";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcBloggerRepository(JdbcTemplate jdbc) {
        this.jdbcTemplate = jdbc;
    }

    @Override
    public Blogger save(Blogger blogger) {
        Long id = blogger.getId();
        if (id == null) {
            long bloggerId = insertAndReturnid(blogger);
            return new Blogger(bloggerId, blogger.getUsername(), blogger.getPassword(), blogger.getFirstName(), blogger.getLastName(), blogger.getEmail());
        } else {
            jdbcTemplate.update(UPDATE_BLOGGER,
                    blogger.getUsername(),
                    blogger.getPassword(),
                    blogger.getFirstName(),
                    blogger.getLastName(),
                    blogger.getEmail(),
                    id);
        }
        return blogger;
    }

    private long insertAndReturnid(Blogger blogger) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("blogger");
        jdbcInsert.setGeneratedKeyName("id");
        Map<String, Object> args = new HashMap<>();
        args.put("username", blogger.getUsername());
        args.put("password", blogger.getPassword());
        args.put("first_name", blogger.getFirstName());
        args.put("last_name", blogger.getLastName());
        args.put("email", blogger.getEmail());
        long id = jdbcInsert.executeAndReturnKey(args).longValue();
        return id;
    }

    @Override
    public Blogger findByUsername(String username) {
        return jdbcTemplate.queryForObject(
                SELECT_BLOGGER + " where username=?",
                new BloggerRowMapper(),
                username);
    }

    private static class BloggerRowMapper implements RowMapper<Blogger> {
        public Blogger mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Blogger(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"));
        }
    }
}

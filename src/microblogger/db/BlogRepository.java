package microblogger.db;

import java.util.List;

public interface BlogRepository {
    List<Blog> findRecent();

    List<Blog> findBlogs(long max, int count);

    Blog findOne(long id);

    void save(Blog blog);
}

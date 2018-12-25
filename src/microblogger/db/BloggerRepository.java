package microblogger.db;

public interface BloggerRepository {
    Blogger save(Blogger bloger);

    Blogger findByUsername(String username);
}

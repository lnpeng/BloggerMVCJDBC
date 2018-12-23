package microblogger.db;

public class BlogNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private long blogId;

    public BlogNotFoundException(long blogId) {
        this.blogId = blogId;
    }

    public long getBlogId() {
        return blogId;
    }
}

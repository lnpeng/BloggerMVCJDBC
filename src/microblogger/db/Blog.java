package microblogger.db;

import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Blog {
	
    private Long id;

    private String message;

    private Date createTime;

    private Blog() {}

    public Blog(String message, Date time) {
        this(null, message, time);
    }

    public Blog(Long id, String message, Date time) {
        this.id = id;
        this.message = message;
        this.createTime = time;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that, "id", "createTime");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "id", "createTime");
    }
}

package class101.foo.io;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "post_shard_8")
public class Post {
    @Id
    private String id;
    private String content;
}

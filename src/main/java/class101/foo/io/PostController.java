package class101.foo.io;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private static int PAGE_SIZE = 20;

    @Autowired
    PostRepository postRepository;

    // 1. 글을 작성한다.
    @PostMapping("/post")
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    // 2. 글 목록을 페이징하여 반환
    @GetMapping("/posts")
    public Page<Post> getPostList(@RequestParam(defaultValue="1")int page) {
        return postRepository.findAll(
                PageRequest.of(page -1,PAGE_SIZE, Sort.by("id").descending())
                //page -1 을 하는 이유는 페이지가 index라서 0번부터 시작하게 되는데 defaultValue가 1이기 때문에 -1을 해주는 것이다. 또한 넘어오는 page의
                //데이터 역시 1부터 시작하기 때문이다.
        );
    }

    // 3. 글 번호로 조회
    @GetMapping("/post/{id}")
    public Post getPostById(@PathVariable("id") Long id){
        return postRepository.findById(id).get(); //findById만 할 경우 타입이 Post가 아니기 때문에 .get()으로 Post타입으로 변환한다.
    }
    
    // 4. 글 내용으로 검색 -> 해당 내용이 포함된 모든 글
    @GetMapping("/search")
    public List<Post> findPostsByContent(@RequestParam String content){
        return postRepository.findByContentContains(content);
    }
}

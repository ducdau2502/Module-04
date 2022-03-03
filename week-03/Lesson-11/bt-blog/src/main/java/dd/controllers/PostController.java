package dd.controllers;

import dd.model.Post;
import dd.service.ICategoryService;
import dd.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    ICategoryService categoryService;

    @Autowired
    IPostService postService;

    @GetMapping
    public ResponseEntity<Iterable<Post>> showAllPost(@RequestParam("search") Optional<String> search) {
        List<Post> posts;
        if (search.isPresent()) {
            posts = (List<Post>) postService.findAllByCategoryId(Long.parseLong(search.get()));
        } else {
            posts = (List<Post>) postService.findAll();
        }

        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findPostById(@PathVariable("id") long id) {
        Optional<Post> post = postService.findById(id);
        if (!post.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> saveCustomer(@RequestBody Post post) {
        return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updateCustomer(@RequestBody Post post, @PathVariable("id") long id) {
        Optional<Post> updatePost = postService.findById(id);
        if (!updatePost.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        post.setId(id);
        post = postService.save(post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deleteCustomer(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.remove(id);
        return new ResponseEntity<>(postOptional.get(), HttpStatus.NO_CONTENT);
    }

}

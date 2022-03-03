package dd.service.impl;

import dd.model.Category;
import dd.model.Post;
import dd.repository.IPostRepository;
import dd.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    IPostRepository postRepository;

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Iterable<Post> findAllByCategory(Category category) {
        return postRepository.findAllByCategory(category);
    }

    @Override
    public Iterable<Post> findAllByCategoryId(long id) {
        return postRepository.findAllByCategoryId(id);
    }

    @Override
    public void deleteAllByCategory(Category category) {
        postRepository.deleteAllByCategory(category);
    }

    @Override
    public void deleteAllByCategoryId(long id) {
        postRepository.deleteAllByCategoryId(id);
    };

    @Override
    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void remove(Long id) {
        postRepository.deleteById(id);
    }
}

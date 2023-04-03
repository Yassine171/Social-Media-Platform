package org.example.service;

import jakarta.ws.rs.NotFoundException;
import org.example.model.Post;
import org.example.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found"));
    }

    public Post createPost(Post post, Long userId) {
        post.setUserId(userId);
        return postRepository.save(post);
    }

    public Post updatePost(Post updatedPost, Long userId) {
        Post existingPost = postRepository.findById(updatedPost.getId())
                .orElseThrow(() -> new NotFoundException("Post not found"));

        if (!existingPost.getUserId().equals(userId)) {
            throw new RuntimeException("User not authorized to update this post");
        }

        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setContent(updatedPost.getContent());

        return postRepository.save(existingPost);
    }

    public void deletePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found"));

        if (!post.getUserId().equals(userId)) {
            throw new RuntimeException("User not authorized to delete this post");
        }

        postRepository.delete(post);
    }
}

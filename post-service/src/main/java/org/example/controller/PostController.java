package org.example.controller;

import org.example.model.Post;
import org.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestHeader(name = "user-id") Long userId) {
        Post createdPost = postService.createPost(post, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/")
    public ResponseEntity<Post> updatePost(@RequestBody Post updatedPost, @RequestHeader(name = "user-id") Long userId) {
        Post post = postService.updatePost(updatedPost, userId);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id, @RequestHeader(name = "user-id") Long userId) {
        postService.deletePost(id, userId);
        return ResponseEntity.noContent().build();
    }
}

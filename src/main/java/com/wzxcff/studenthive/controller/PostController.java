package com.wzxcff.studenthive.controller;

import com.wzxcff.studenthive.dto.request.PostRequest;
import com.wzxcff.studenthive.dto.response.PostResponse;
import com.wzxcff.studenthive.model.entity.User;
import com.wzxcff.studenthive.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<Page<PostResponse>> getPosts(@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(postService.getAllPosts(pageable));
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();

        PostResponse postResponse = postService.createPost(postRequest, currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponse);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();

        postService.deletePost(postId, currentUser);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long postId, @RequestBody PostRequest postRequest, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();

        PostResponse postResponse = postService.updatePost(postId, postRequest, currentUser);
        return ResponseEntity.ok(postResponse);
    }
}

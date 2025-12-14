package com.wzxcff.studenthive.controller;

import com.wzxcff.studenthive.dto.request.PostRequest;
import com.wzxcff.studenthive.dto.response.PostResponse;
import com.wzxcff.studenthive.model.entity.User;
import com.wzxcff.studenthive.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<PostResponse>> getPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(postService.getAllPosts(page, size));
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();

        PostResponse postResponse = postService.createPost(postRequest, currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(postResponse);
    }
}

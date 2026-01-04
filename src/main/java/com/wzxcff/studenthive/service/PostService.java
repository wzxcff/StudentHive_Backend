package com.wzxcff.studenthive.service;

import com.wzxcff.studenthive.dto.request.PostRequest;
import com.wzxcff.studenthive.dto.response.PostResponse;
import com.wzxcff.studenthive.model.entity.Post;
import com.wzxcff.studenthive.model.entity.User;
import com.wzxcff.studenthive.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Page<PostResponse> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable).map(post -> new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor().getUsername(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.isPinned()
        ));
    }

    public PostResponse createPost(PostRequest postRequest, User author) {
        Post post = Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .author(author)
                .isPinned(false)
                .build();

        Post savedPost = postRepository.save(post);

        return new PostResponse(
                savedPost.getId(),
                savedPost.getTitle(),
                savedPost.getContent(),
                savedPost.getAuthor().getRealUsername(),
                savedPost.getCreatedAt(),
                savedPost.getUpdatedAt(),
                savedPost.isPinned()
        );
    }

    public void deletePost(Long postId, User currentUser) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getAuthor().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You are not allowed to delete this post");
        }
        postRepository.delete(post);
    }

    public PostResponse updatePost(Long postId, PostRequest postRequest, User author) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getAuthor().getId().equals(author.getId())) {
            throw new RuntimeException("You are not allowed to update this post");
        }
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        Post updatedPost = postRepository.save(post);
        return new PostResponse(
                updatedPost.getId(),
                updatedPost.getTitle(),
                updatedPost.getContent(),
                updatedPost.getAuthor().getRealUsername(),
                updatedPost.getCreatedAt(),
                updatedPost.getUpdatedAt(),
                updatedPost.isPinned()
        );
    }
}

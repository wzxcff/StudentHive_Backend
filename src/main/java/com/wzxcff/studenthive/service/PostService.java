package com.wzxcff.studenthive.service;

import com.wzxcff.studenthive.dto.response.PostResponse;
import com.wzxcff.studenthive.model.entity.Post;
import com.wzxcff.studenthive.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Page<PostResponse> getAllPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<Post> postsPage = postRepository.findAll(pageable);

        return postsPage.map(post -> new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor().getUsername(),
                post.getCreatedAt(),
                post.isPinned()
        ));
    }
}

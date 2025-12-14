package com.wzxcff.studenthive.repository;

import com.wzxcff.studenthive.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByAuthorId(Long authorId, Pageable pageable);
}

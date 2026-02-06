package com.wzxcff.studenthive.repository;

import com.wzxcff.studenthive.model.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @EntityGraph(attributePaths = {"author"})
    Page<Comment> findAllByPostId(Long postId, Pageable pageable);

    @EntityGraph(attributePaths = {"post"})
    Page<Comment> findAllByAuthorId(Long authorId, Pageable pageable);
}

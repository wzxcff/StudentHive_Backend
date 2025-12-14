package com.wzxcff.studenthive.repository;

import com.wzxcff.studenthive.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

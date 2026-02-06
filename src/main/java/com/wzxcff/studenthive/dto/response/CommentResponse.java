package com.wzxcff.studenthive.dto.response;

import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        String content,
        LocalDateTime createdAt,
        String authorUsername
) {}

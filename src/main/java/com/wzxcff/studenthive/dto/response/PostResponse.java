package com.wzxcff.studenthive.dto.response;


import java.time.LocalDateTime;

public record PostResponse (
    Long id,
    String title,
    String content,
    String authorUsername,
    LocalDateTime createdAt,
    boolean isPinned
) {}

package com.wzxcff.studenthive.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public record PostResponse (
    Long id,
    String title,
    String content,
    String authorUsername,
    LocalDateTime createdAt,
    boolean isPinned
) {}

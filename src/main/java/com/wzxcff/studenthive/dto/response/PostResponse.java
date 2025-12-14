package com.wzxcff.studenthive.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    Long id;
    String title;
    String content;
    String authorUsername;
    LocalDateTime createdAt;
    boolean isPinned;
}

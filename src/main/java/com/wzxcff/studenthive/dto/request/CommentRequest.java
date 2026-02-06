package com.wzxcff.studenthive.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentRequest (
        @NotBlank(message = "Comment cannot be empty")
        @Size(max = 1000, message = "Comment is too long")
        String content
) {}

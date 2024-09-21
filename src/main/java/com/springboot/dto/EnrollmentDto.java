package com.springboot.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EnrollmentDto {
    private Long id;
    private LocalDateTime enrollmentDate;

    private Long courseId;
    private Long studentId;
}

package com.radian.jpaTutorial.JpaTuts.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfessorDto {
    private Long id;
    

    private String title;
    
    private Long subjectId;  // ✅ Just the ID, not DTO
    
    @Builder.Default
    private List<Long> studentIds = new ArrayList<>();  // ✅ Just IDs, not DTOs
}

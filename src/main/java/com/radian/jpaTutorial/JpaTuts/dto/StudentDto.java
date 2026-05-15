package com.radian.jpaTutorial.JpaTuts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)   ///not to include null values in response
public class StudentDto {
    private Long id;

    private String name;
    
    @Builder.Default
    private List<Long> subjectIds = new ArrayList<>();
    
    @Builder.Default
    private List<Long> professorIds = new ArrayList<>();
    
    private Long admissionListId;
}

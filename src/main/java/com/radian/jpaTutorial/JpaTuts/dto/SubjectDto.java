package com.radian.jpaTutorial.JpaTuts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectDto {
    private Long id;
    private String name;
    private List<Long> professorIds;
    private List<Long> studentIds;
}

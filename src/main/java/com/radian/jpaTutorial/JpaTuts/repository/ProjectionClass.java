package com.radian.jpaTutorial.JpaTuts.repository;

// Interface-based projection:
// Spring Data JPA generates an implementation at runtime and fills these values
// from the column aliases returned by the query.
public interface ProjectionClass {
    String getTitle();
    String getSku();
}

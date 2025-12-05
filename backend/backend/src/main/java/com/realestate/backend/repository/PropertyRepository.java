package com.realestate.backend.repository;

import com.realestate.backend.entity.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("SELECT p FROM Property p WHERE " +
            "LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.city) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.type) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Property> search(@Param("keyword") String keyword, Pageable pageable);
}





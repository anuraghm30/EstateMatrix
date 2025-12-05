package com.realestate.backend.service;

import com.realestate.backend.entity.Property;
import org.springframework.data.domain.Page;

public interface PropertyService {

    Page<Property> getAllProperties(int page, int size);

    Property create(Property property);

    Page<Property> searchProperties(String keyword, int page, int size);

    void deleteProperty(Long id);
}





package com.realestate.backend.service.impl;

import com.realestate.backend.entity.Property;
import com.realestate.backend.repository.PropertyRepository;
import com.realestate.backend.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    // Get paginated properties
    @Override
    public Page<Property> getAllProperties(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return propertyRepository.findAll(pageable);
    }

    // Create a new property
    @Override
    public Property create(Property property) {
        return propertyRepository.save(property);
    }

    // Search properties using the new search() from repository
    @Override
    public Page<Property> searchProperties(
            String keyword,
            int page,
            int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        if (keyword == null || keyword.trim().isEmpty()) {
            return propertyRepository.findAll(pageable);
        }
        return propertyRepository.search(keyword, pageable);
    }

    // Delete property by ID
    @Override
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}


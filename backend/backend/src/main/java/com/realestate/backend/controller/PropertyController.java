package com.realestate.backend.controller;

import com.realestate.backend.entity.Property;
import com.realestate.backend.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import java.util.Map;


@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "http://localhost:3000")
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;

    // GET paginated properties
    @GetMapping
    public ResponseEntity<?> getAllProperties(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Property> result = propertyRepository.findAll(pageable);

        return ResponseEntity.ok(
                Map.of(
                        "data", result
                )
        );
    }

    // Search properties
    @GetMapping("/search")
    public ResponseEntity<?> searchProperties(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Property> result = propertyRepository.search(keyword, pageable);

        return ResponseEntity.ok(
                Map.of(
                        "data", result
                )
        );
    }

    // Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        return propertyRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}






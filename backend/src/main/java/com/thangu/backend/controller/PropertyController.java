package com.thangu.backend.controller;

import com.thangu.backend.dto.request.PropertyRequest;
import com.thangu.backend.dto.response.PropertyResponse;
import com.thangu.backend.service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/properties")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyService propertyService;

    @PostMapping
    public ResponseEntity<PropertyResponse> create(@Valid @RequestBody PropertyRequest request) {
        var response = propertyService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponse> getById(@PathVariable Long id) {
        var response = propertyService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PropertyResponse>> getAll() {
        var response = propertyService.getAll();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyResponse> update(@PathVariable Long id, @Valid @RequestBody PropertyRequest request) {
        var response = propertyService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        propertyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.thangu.backend.controller;

import com.thangu.backend.dto.request.PropertyRequest;
import com.thangu.backend.dto.response.PropertyResponse;
import com.thangu.backend.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Property Api", description = "Operations for managing property listings")
@RestController
@RequestMapping("api/v1/properties")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyService propertyService;

    @Operation(summary = "Create Property", description = "Create new property listing")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Property created"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<PropertyResponse> create(@Valid @RequestBody PropertyRequest request) {
        var response = propertyService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get Property by id", description = "Get property by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Property fetched"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponse> getById(@PathVariable Long id) {
        var response = propertyService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get all Property", description = "Get all property")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Property fetched"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public ResponseEntity<List<PropertyResponse>> getAll() {
        var response = propertyService.getAll();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update Property", description = "Update property listing")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Property updated"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PropertyResponse> update(@PathVariable Long id, @Valid @RequestBody PropertyRequest request) {
        var response = propertyService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete Property", description = "Delete property")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Property created"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        propertyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

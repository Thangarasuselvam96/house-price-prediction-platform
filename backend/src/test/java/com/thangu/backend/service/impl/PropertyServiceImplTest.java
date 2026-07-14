package com.thangu.backend.service.impl;

import com.thangu.backend.dto.request.PropertyRequest;
import com.thangu.backend.dto.response.PropertyResponse;
import com.thangu.backend.entity.Property;
import com.thangu.backend.exception.ResourceNotFoundException;
import com.thangu.backend.mapper.PropertyMapper;
import com.thangu.backend.repository.PropertyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PropertyServiceImplTest {
    @Mock
    private PropertyRepository repository;

    @Mock
    private PropertyMapper mapper;

    @InjectMocks
    private PropertyServiceImpl service;

    @Test
    void shouldSavePropertySuccessfully() {
        // Arrange
        PropertyRequest request = PropertyRequest.builder()
                .title("Test title").build();
        Property property = Property.builder().title("Test title").build();
        PropertyResponse propertyResponse = PropertyResponse.builder().title("Test title").build();

        when(mapper.toEntity(request)).thenReturn(property);
        when(repository.save(property)).thenReturn(property);
        when(mapper.toResponse(property)).thenReturn(propertyResponse);

        //Act
        var response = service.save(request);

        //Assert
        assertNotNull(response);
        assertEquals("Test title", response.getTitle());

        verify(mapper).toEntity(request);
        verify(repository).save(property);
        verify(mapper).toResponse(property);
    }

    @Test
    void shouldReturnPropertyWhenPropertyExists() {
        // Arrange
        Long id = 1L;
        Property property = Property.builder().title("test").build();
        PropertyResponse propertyResponse = PropertyResponse.builder().title("test").build();

        when(repository.findById(id)).thenReturn(Optional.of(property));
        when(mapper.toResponse(property)).thenReturn(propertyResponse);

        // Act
        var response = service.getById(id);

        // Assert
        assertNotNull(response);
        assertEquals("test", response.getTitle());

        verify(repository).findById(id);
        verify(mapper).toResponse(property);

    }

    @Test
    void shouldThrowResourceNotFoundExceptionWhenPropertyDoesNotExist() {
        // Arrange
        Long id = 1L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> service.getById(id));

        // Assert
        assertEquals("Property not found", exception.getMessage());

        verify(repository).findById(id);
        verify(mapper, never()).toResponse(any());
    }

    @Test
    void shouldReturnAllProperties() {

        // Arrange
        List<Property> properties = List.of(
                Property.builder().id(1L).title("Villa").build(),
                Property.builder().id(2L).title("Flat").build()
        );

        List<PropertyResponse> responses = List.of(
                PropertyResponse.builder().id(1L).title("Villa").build(),
                PropertyResponse.builder().id(2L).title("Flat").build()
        );

        when(repository.findAll()).thenReturn(properties);
        when(mapper.toResponseList(properties)).thenReturn(responses);

        // Act
        List<PropertyResponse> result = service.getAll();

        // Assert
        assertEquals(2, result.size());

        verify(repository).findAll();
        verify(mapper).toResponseList(properties);
    }

    @Test
    void shouldUpdatePropertySuccessfully() {

        // Arrange
        Long id = 1L;

        PropertyRequest request = PropertyRequest.builder()
                .title("Updated Villa")
                .city("Bangalore")
                .build();

        Property property = Property.builder()
                .id(id)
                .title("Old Villa")
                .build();

        PropertyResponse response = PropertyResponse.builder()
                .id(id)
                .title("Updated Villa")
                .city("Bangalore")
                .build();

        when(repository.findById(id))
                .thenReturn(Optional.of(property));

        when(repository.save(property))
                .thenReturn(property);

        when(mapper.toResponse(property))
                .thenReturn(response);

        // Act
        PropertyResponse result = service.update(id, request);

        // Assert
        assertEquals("Updated Villa", result.getTitle());

        verify(repository).findById(id);
        verify(repository).save(property);
        verify(mapper).toResponse(property);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingProperty() {

        Long id = 1L;

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.update(id, PropertyRequest.builder().build())
        );

        verify(repository).findById(id);
        verify(repository, never()).save(any());
    }

    @Test
    void shouldDeletePropertySuccessfully() {

        Long id = 1L;

        Property property = Property.builder()
                .id(id)
                .build();

        when(repository.findById(id))
                .thenReturn(Optional.of(property));

        // Act
        service.delete(id);

        // Assert
        verify(repository).findById(id);
        verify(repository).delete(property);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistingProperty() {

        Long id = 1L;

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> service.delete(id)
        );

        verify(repository).findById(id);
        verify(repository, never()).delete(any());
    }
}
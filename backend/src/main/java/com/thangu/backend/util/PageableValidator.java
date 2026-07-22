package com.thangu.backend.util;

import com.thangu.backend.dto.request.PropertySearchRequest;
import com.thangu.backend.exception.BadRequestException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Set;

public final class PageableValidator {
    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "price",
            "createdAt",
            "areaSqft",
            "city",
            "title"
    );

    private PageableValidator() {}

    public static void validate(PropertySearchRequest request) {
        if(!ALLOWED_SORT_FIELDS.contains(request.getSortBy())) {
            throw new BadRequestException("Invalid sort fields");
        }
    }
}

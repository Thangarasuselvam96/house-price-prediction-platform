package com.thangu.backend.mapper;

import com.thangu.backend.dto.response.PageResponse;
import org.springframework.data.domain.Page;

public final class PageMapper {
    private PageMapper() {

    }
    public static <T> PageResponse<T> from(Page<T> page) {
        return PageResponse.<T>builder()
                .content(page.getContent())
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }
}

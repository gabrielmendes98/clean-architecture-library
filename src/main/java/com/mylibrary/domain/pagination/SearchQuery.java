package com.mylibrary.domain.pagination;

public record SearchQuery(
        int page,
        String search
) {
}

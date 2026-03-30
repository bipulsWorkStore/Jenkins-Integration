package com.ukss.BookApiWithH2DBRecord.dto;

public record BookResponse(
        Long id,
        String name,
        double price
) {
}

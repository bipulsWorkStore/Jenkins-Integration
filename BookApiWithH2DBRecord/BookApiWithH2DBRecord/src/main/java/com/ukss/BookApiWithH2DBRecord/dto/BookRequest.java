package com.ukss.BookApiWithH2DBRecord.dto;

public record BookRequest(
        String name,
        double price
) {
}

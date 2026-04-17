package com.example.backend.catalogue.domain.dto;

import java.math.BigDecimal;

public record PriceDto(BigDecimal amount, String currency) {}

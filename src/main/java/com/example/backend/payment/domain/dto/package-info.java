/**
 * Data transfer objects for the Payment module.
 * <p>
 * This package is a {@link org.springframework.modulith.NamedInterface named interface}
 * and contains DTOs used for inter-module communication and API request/response payloads.
 * These objects decouple the module's internal domain model from its external contracts.
 * <p>
 * Other modules may reference this interface via {@code payment :: dtos} in their
 * {@link org.springframework.modulith.ApplicationModule#allowedDependencies() allowedDependencies}.
 * <p>
 * Other modules should use these DTOs when interacting with the Payment module's API.
 */
@org.springframework.modulith.NamedInterface("dtos")
package com.example.backend.payment.domain.dto;

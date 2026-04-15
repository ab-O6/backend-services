/**
 * Public API of the Payment module.
 * <p>
 * This package is a {@link org.springframework.modulith.NamedInterface named interface}
 * and exposes the module's public contracts to other modules. It contains REST controllers,
 * service interfaces, and any other types that form the module's external-facing API.
 * <p>
 * Other modules may reference this interface via {@code payment :: api} in their
 * {@link org.springframework.modulith.ApplicationModule#allowedDependencies() allowedDependencies}.
 * <p>
 * Other modules should depend only on types declared in this package.
 */
@org.springframework.modulith.NamedInterface("api")
package com.example.backend.payment.api;

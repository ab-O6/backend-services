/**
 * The Catalogue module.
 * <p>
 * This module encapsulates all functionality related to catalogue management
 * within the application. It exposes a public API via the {@code api} sub-package
 * and keeps its internal implementation details hidden from other modules.
 * <p>
 * Named interfaces exposed by this module:
 * <ul>
 *   <li>{@code api} &mdash; Public API contracts (services, controllers)</li>
 *   <li>{@code events} &mdash; Domain events published by this module</li>
 *   <li>{@code dtos} &mdash; Data transfer objects for inter-module communication</li>
 * </ul>
 *
 * @see com.example.backend.catalogue.api
 */
@org.springframework.modulith.ApplicationModule(
    displayName = "Catalogue Management"
)
package com.example.backend.catalogue;

/**
 * Domain events published by the Customer module.
 * <p>
 * This package is a {@link org.springframework.modulith.NamedInterface named interface}
 * and contains event classes that are published by the Customer module and can be
 * consumed by other modules. Events in this package represent significant state changes
 * or occurrences within the customer domain.
 * <p>
 * Other modules may reference this interface via {@code customer :: events} in their
 * {@link org.springframework.modulith.ApplicationModule#allowedDependencies() allowedDependencies}.
 * <p>
 * Other modules may listen for these events using Spring's
 * {@code @ApplicationModuleListener} or {@code @EventListener} annotations.
 */
@org.springframework.modulith.NamedInterface("events")
package com.example.backend.customer.domain.event;

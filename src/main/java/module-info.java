module backend.services {

    requires java.sql;

    requires jdk.unsupported;

    requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires spring.aop;
    requires spring.tx;
    requires spring.jdbc;
    requires spring.data.jpa;

    requires spring.boot;
    requires spring.boot.autoconfigure;

    requires spring.modulith.core;
    requires spring.modulith.runtime;

    /*
     * requires spring.modulith.events.api;
     * requires spring.modulith.events.core;
     * requires spring.modulith.events.jdbc;
     */

    requires spring.aspects;
    requires spring.web;
    requires micrometer.tracing;
    requires jakarta.persistence;
    requires static lombok;
    requires spring.modulith.api;

    // --- Root package ---
    opens com.example.backend to spring.core, spring.beans, spring.context;

    // --- Cart module ---
    opens com.example.backend.cart to spring.core, spring.beans, spring.context;
    opens com.example.backend.cart.api to spring.core, spring.beans, spring.context, spring.web;
    opens com.example.backend.cart.domain.dto to spring.core, spring.beans, spring.context;
    opens com.example.backend.cart.domain.event to spring.core, spring.beans, spring.context;

    // --- Catalogue module ---
    opens com.example.backend.catalogue to spring.core, spring.beans, spring.context;
    opens com.example.backend.catalogue.api to spring.core, spring.beans, spring.context, spring.web;
    opens com.example.backend.catalogue.domain.dto to spring.core, spring.beans, spring.context;
    opens com.example.backend.catalogue.domain.event to spring.core, spring.beans, spring.context;

    // --- Customer module ---
    opens com.example.backend.customer to spring.core, spring.beans, spring.context;
    opens com.example.backend.customer.api to spring.core, spring.beans, spring.context, spring.web;
    opens com.example.backend.customer.domain.dto to spring.core, spring.beans, spring.context;
    opens com.example.backend.customer.domain.event to spring.core, spring.beans, spring.context;

    // --- Inventory module ---
    opens com.example.backend.inventory to spring.core, spring.beans, spring.context;
    opens com.example.backend.inventory.api to spring.core, spring.beans, spring.context, spring.web;
    opens com.example.backend.inventory.domain.dto to spring.core, spring.beans, spring.context;
    opens com.example.backend.inventory.domain.event to spring.core, spring.beans, spring.context;

    // --- Order module ---
    opens com.example.backend.order to spring.core, spring.beans, spring.context;
    opens com.example.backend.order.api to spring.core, spring.beans, spring.context, spring.web;
    opens com.example.backend.order.domain.dto to spring.core, spring.beans, spring.context;
    opens com.example.backend.order.domain.event to spring.core, spring.beans, spring.context;

    // --- Payment module ---
    opens com.example.backend.payment to spring.core, spring.beans, spring.context;
    opens com.example.backend.payment.api to spring.core, spring.beans, spring.context, spring.web;
    opens com.example.backend.payment.domain.dto to spring.core, spring.beans, spring.context;
    opens com.example.backend.payment.domain.event to spring.core, spring.beans, spring.context;

    // --- Security module ---
    opens com.example.backend.security to spring.core, spring.beans, spring.context;
}

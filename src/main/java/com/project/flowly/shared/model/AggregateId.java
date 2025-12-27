package com.project.flowly.shared.model;

import java.io.Serializable;
import java.util.UUID;

public abstract class AggregateId implements Serializable {
    private final String id;

    protected AggregateId() {
        this.id = UUID.randomUUID().toString();
    }

    protected AggregateId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AggregateId that = (AggregateId) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id;
    }
}
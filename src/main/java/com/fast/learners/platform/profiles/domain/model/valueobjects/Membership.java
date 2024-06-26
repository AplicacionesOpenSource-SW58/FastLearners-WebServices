package com.fast.learners.platform.profiles.domain.model.valueobjects;

public record Membership (String membership) {

    public Membership() {
        this(null);
    }

    public Membership {
        if (membership == null || membership.isBlank()) {
            throw new IllegalArgumentException("Membership type cannot be null or blank");
        }
        if (!membership.equals("Basic") && !membership.equals("Regular") && !membership.equals("Premium")) {
            throw new IllegalArgumentException("Not a valid membership type");
        }
    }
    public String getMembership() {
        return String.format("%s", membership) ;
    }

}

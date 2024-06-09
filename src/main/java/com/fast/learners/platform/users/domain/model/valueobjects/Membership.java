package com.fast.learners.platform.users.domain.model.valueobjects;

public record Membership (String type) {

    public Membership() {
        this(null);
    }

    public Membership {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("Membership type cannot be null or blank");
        }
        if (!type.equals("Basic") && !type.equals("Regular") && !type.equals("Premium")) {
            throw new IllegalArgumentException("Not a valid membership type");
        }
    }
    public String getMembership() {
        return String.format("%s", type) ;
    }

}

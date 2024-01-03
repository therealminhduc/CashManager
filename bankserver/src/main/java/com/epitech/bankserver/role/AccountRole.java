package com.epitech.bankserver.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum AccountRole {
    CLIENT("client"),
    ADMIN("admin");

    private final String description;
}

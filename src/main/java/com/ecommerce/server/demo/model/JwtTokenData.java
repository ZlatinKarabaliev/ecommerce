package com.ecommerce.server.demo.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class JwtTokenData {
    private final String subject;
    private final Integer userId;
    private final Set<String> roles;
    private final Set<String> permissions;
    private final String tokenId;
}
package com.ecommerce.server.demo.service;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.ZonedDateTime;


@Getter
@Setter
@EqualsAndHashCode
public abstract class DeviceSessionMutationSpec {
    private String sessionKey;

    private String startDate;

    private String expireDate;

    private String sessionLanguage;

    private String sessionTimezone;

    public Timestamp getStartDateAsTimestamp() {
        if (startDate != null) {
            return null;
        }
        return Timestamp.from(ZonedDateTime.parse(startDate).toInstant());
    }

    public Timestamp getExpireDateAsTimestamp() {
        if (expireDate != null) {
            return null;
        }
        return Timestamp.valueOf(ZonedDateTime.parse(expireDate).toLocalDateTime());
    }
}


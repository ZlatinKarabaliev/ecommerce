package com.ecommerce.server.demo.model.tsc;

import com.ecommerce.server.demo.model.AbstractDomainEntity;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "tsc02_sessions")
public class DeviceSession extends AbstractDomainEntity implements Serializable {

    private static final long serialVersionUID = -4481951772892072657L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "csc02_id", unique = true, nullable = false, precision = 0)
    private Integer id;


    @Column(name = "csc02_session_key")
    private String sessionKey;

    @Column(name = "csc02_start_date", columnDefinition = "timestamp")
    private Timestamp startDate;

    @Column(name = "csc02_expire_date", columnDefinition = "timestamp")
    private Timestamp expireDate;

    @Column(name = "csc02_session_language")
    private String sessionLanguage;

    @Column(name = "csc02_timezone")
    private String sessionTimezone;
}
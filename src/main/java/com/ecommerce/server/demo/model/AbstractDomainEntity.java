package com.ecommerce.server.demo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDate;

;

@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public abstract class AbstractDomainEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Integer version;

    @CreationTimestamp
    @Column(name = "date_created")
    private LocalDate dateCreated;


    @UpdateTimestamp
    @Column(name = "date_updated")
    private LocalDate dateUpdated;

}

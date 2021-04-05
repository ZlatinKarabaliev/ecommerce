package com.ecommerce.server.demo.model.tsc;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "tsc03_user_roles")
public class Role implements Serializable {
    private static final long serialVersionUID = -4481951772892072657L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "csc03_id", unique = true, nullable = false, precision = 0)
    private Integer id;

    @NotEmpty(message = "Missing role name")
    @Column(name = "csc03_name")
    private String name;

    @NotEmpty(message = "Missing role description")
    @Column(name = "csc03_description")
    private String description;

    @NotNull(message = "Missing role activation status")
    @Column(name = "csc03_is_active")
    private Boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade({ org.hibernate.annotations.CascadeType.REFRESH })
    @JoinTable(name = "tsc05_role_permission_map",
            joinColumns = { @JoinColumn(name = "csc03_role_id")},
            inverseJoinColumns = { @JoinColumn(name = "csc04_permission_id") })
    private Set<Permission> permissions;

}

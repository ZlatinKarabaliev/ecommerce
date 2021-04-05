package com.ecommerce.server.demo.model.tsc;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tsc04_permission_elements")
public class Permission implements Serializable {
    private static final long serialVersionUID = -4481951772892072657L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "csc04_id", unique = true, nullable = false, precision = 0)
    private Integer id;

    @Column(name = "csc04_name")
    private String name;

//    @OneToOne
//    @Cascade({org.hibernate.annotations.CascadeType.REFRESH})
//    @JoinColumn(name = "cnm01_type_id")
//    private Code typeId;

    @Column(name = "csc03_description")
    private String description;
}

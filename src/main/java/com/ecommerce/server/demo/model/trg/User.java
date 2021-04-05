package com.ecommerce.server.demo.model.trg;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.usertype.UserType;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;
    //@JsonIgnore
    @Column(name = "PASSWORD")
    private String password;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "CREATED_TIME")
    private Date creationTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_TIME")
    private Date updatedTime;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "DOB")
    private Date dateofBirth;

    @Transient
    private String dateOfBirthString;

}

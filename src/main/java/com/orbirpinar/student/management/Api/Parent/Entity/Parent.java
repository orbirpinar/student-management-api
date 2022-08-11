package com.orbirpinar.student.management.Api.Parent.Entity;

import com.orbirpinar.student.management.Api.Student.Entity.Student;
import com.orbirpinar.student.management.Utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Parent extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid_")
    @GenericGenerator(name="uuid_",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String firstname;

    private String lastname;

    private String birthDate;

    private String phone;

    private String job;

    private String parentType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
}

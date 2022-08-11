package com.orbirpinar.student.management.Api.Subject.Entity;

import com.orbirpinar.student.management.Api.Class.Entity.ClassRoom;
import com.orbirpinar.student.management.Api.Examination.Entity.Exam;
import com.orbirpinar.student.management.Api.Teacher.Entity.Teacher;
import com.orbirpinar.student.management.Utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Subject extends BaseEntity implements Serializable {


    @Id
    @GeneratedValue(generator = "uuid_")
    @GenericGenerator(name="uuid_",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String name;

    @ManyToMany(mappedBy = "subjects")
    private Set<ClassRoom> classes;

    @ManyToMany(mappedBy = "subjects")
    private Set<Teacher> teachers;

    @OneToMany(mappedBy = "subject",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Exam> exams;

}

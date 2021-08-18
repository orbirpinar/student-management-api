package com.orbirpinar.student.management.Api.Class.Entity;

import com.orbirpinar.student.management.Api.Examination.Entity.Exam;
import com.orbirpinar.student.management.Api.Student.Entity.Student;
import com.orbirpinar.student.management.Api.Subject.Entity.Subject;
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
public class Class extends BaseEntity implements Serializable {


    @Id
    @GeneratedValue(generator = "uuid_")
    @GenericGenerator(name="uuid_",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String grade;

    private String branch;


    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(	name = "class_teacher",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private Set<Teacher> teachers;


    @OneToMany(mappedBy = "_class",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Student> students;


    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(	name = "class_subject",
            joinColumns = @JoinColumn(name = "class_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects;

    @OneToMany(mappedBy = "_class",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Exam> exams;






}

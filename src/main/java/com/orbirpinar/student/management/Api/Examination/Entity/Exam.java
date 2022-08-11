package com.orbirpinar.student.management.Api.Examination.Entity;

import com.orbirpinar.student.management.Api.Class.Entity.ClassRoom;
import com.orbirpinar.student.management.Api.ExamResult.Entity.ExamResult;
import com.orbirpinar.student.management.Api.Subject.Entity.Subject;
import com.orbirpinar.student.management.Utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Exam extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid_")
    @GenericGenerator(name="uuid_",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private LocalDate examDate;

    private LocalTime examTime;

    @Enumerated(EnumType.STRING)
    private ExamType examType;

    @Enumerated(EnumType.STRING)
    private Semester semester;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "class_id", nullable = false)
    private ClassRoom classRoom;

    @OneToMany(mappedBy = "exam")
    private Set<ExamResult> results;
}

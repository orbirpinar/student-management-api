package com.orbirpinar.student.management.Api.ExamResult.Entity;

import com.orbirpinar.student.management.Api.Examination.Entity.Exam;
import com.orbirpinar.student.management.Utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class ExamResult extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid_")
    @GenericGenerator(name="uuid_",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    private Boolean mark;
}

package com.orbirpinar.student.management.Api.teacher.entity;

import com.orbirpinar.student.management.Api.Class.Entity.ClassRoom;
import com.orbirpinar.student.management.Api.User.DTO.UserViewDto;
import com.orbirpinar.student.management.Api.common.Gender;
import com.orbirpinar.student.management.Api.Subject.Entity.Subject;
import com.orbirpinar.student.management.Api.User.Entity.User;
import com.orbirpinar.student.management.Api.common.BaseEntity;
import com.orbirpinar.student.management.Api.teacher.dto.TeacherViewDto;
import com.orbirpinar.student.management.Utils.Transformer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Teacher extends BaseEntity implements Serializable {


    @Id
    @GeneratedValue(generator = "uuid_")
    @GenericGenerator(name="uuid_",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String graduatedSchool;

    private String department;


    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @ManyToMany(mappedBy = "teachers")
    private Set<ClassRoom> classes;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinTable(	name = "teacher_subject",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects;

    public TeacherViewDto toViewDto() {
        TeacherViewDto teacherViewDto = new TeacherViewDto();
        teacherViewDto.setId(id);
        teacherViewDto.setDepartment(department);
        teacherViewDto.setBirthDate(birthDate);
        teacherViewDto.setFirstName(user.getFirstName());
        teacherViewDto.setLastName(user.getLastName());
        teacherViewDto.setGraduateSchool(graduatedSchool);
        teacherViewDto.setGender(gender);
        teacherViewDto.setUser(Transformer.map(user, UserViewDto.class));
        return teacherViewDto;
    }

}

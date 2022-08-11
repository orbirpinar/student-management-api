package com.orbirpinar.student.management.Api.Student.Entity;

import com.orbirpinar.student.management.Api.Class.Entity.ClassRoom;
import com.orbirpinar.student.management.Api.Parent.Entity.Parent;
import com.orbirpinar.student.management.Api.Student.DTO.StudentViewDto;
import com.orbirpinar.student.management.Utils.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Student extends BaseEntity implements Serializable {


    @Id
    @GeneratedValue(generator = "uuid_")
    @GenericGenerator(name="uuid_",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String firstname;

    private String lastname;


    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthDate;

    private String phoneNumber;


    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Parent> parent;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "class_id", nullable = false)
    private ClassRoom classRoom;

    public  StudentViewDto toViewDto() {
        StudentViewDto studentViewDto = new StudentViewDto();
        studentViewDto.setId(this.id);
        studentViewDto.setFirstname(this.firstname);
        studentViewDto.setLastname(this.lastname);
        studentViewDto.setName(this.firstname + " "  + this.lastname);
        studentViewDto.setGender(this.gender);
        studentViewDto.setBirthDate(this.birthDate);
        studentViewDto.setPhoneNumber(this.phoneNumber);
        studentViewDto.setClassRoom(this.classRoom.toString());
        return studentViewDto;
    }




}
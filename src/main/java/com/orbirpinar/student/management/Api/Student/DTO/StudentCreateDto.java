package com.orbirpinar.student.management.Api.Student.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orbirpinar.student.management.Api.Class.Entity.ClassRoom;
import com.orbirpinar.student.management.Api.common.Gender;
import com.orbirpinar.student.management.Api.Student.Entity.Student;
import com.orbirpinar.student.management.Exception.Error;
import com.orbirpinar.student.management.Exception.StudentManagementBusinessException;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class StudentCreateDto implements Serializable {

    private String firstname;

    private String lastname;

    private Gender gender;

    private LocalDate birthDate;

    private String phoneNumber;

    @NotNull(message = "Class cannot be null")
    @JsonProperty("class")
    private String classRoom;

    public static Student toEntity(StudentCreateDto studentToBeCreated) {
        Student student = new Student();
        student.setFirstname(studentToBeCreated.getFirstname());
        student.setLastname(studentToBeCreated.getLastname());
        student.setBirthDate(studentToBeCreated.getBirthDate());
        student.setGender(studentToBeCreated.getGender());
        student.setPhoneNumber(studentToBeCreated.getPhoneNumber());
        String[] classArray = studentToBeCreated.getClassRoom().split("[-/]");
        if(classArray.length != 2) {
            throw new StudentManagementBusinessException(Error.INVALID_CLASS_FORMAT);
        }
        String grade = classArray[0];
        String branch = classArray[1];
        ClassRoom classRoom = new ClassRoom();
        classRoom.setGrade(grade);
        classRoom.setBranch(branch);
        student.setClassRoom(classRoom);
        return student;
    }
}

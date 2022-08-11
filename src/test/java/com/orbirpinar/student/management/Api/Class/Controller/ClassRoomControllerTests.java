package com.orbirpinar.student.management.Api.Class.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orbirpinar.student.management.Api.Class.DTO.ClassViewDto;
import com.orbirpinar.student.management.Api.Class.Entity.ClassRoom;
import com.orbirpinar.student.management.Utils.Transformer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClassRoomController.class)
@RunWith(SpringRunner.class)
public class ClassRoomControllerTests {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @MockBean
    private ClassRoomController classController;

    @Test
    public void whenCallClassList_thenReturns200() throws Exception {
        mockMvc.perform(get("/api/classes"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGettingIndividualClass_givenNotExistingClassId_thenReturns400() throws Exception {
        mockMvc.perform(get("/api/classes/123123123"))
                .andExpect(status().is4xxClientError());
    }


    @Test
    public void whenRequestingAllClassesEndpoint_thenReturnListOfClasses() throws Exception {

        ClassRoom _class = new ClassRoom();
        _class.setGrade("5");
        _class.setBranch("A");
        List<ClassRoom> classList = List.of(_class);
        List<ClassViewDto> classViewDtos = Transformer.mapAll(classList, ClassViewDto.class);
        given(classController.getAll()).willReturn(ResponseEntity.ok(classViewDtos));
        mockMvc.perform(get("/api/classes").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].grade", is(_class.getGrade())));
    }
}

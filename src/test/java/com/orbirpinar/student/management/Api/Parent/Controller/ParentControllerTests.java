package com.orbirpinar.student.management.Api.Parent.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orbirpinar.student.management.Api.Parent.DTO.ParentViewDto;
import com.orbirpinar.student.management.Api.Parent.Entity.Parent;
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

import static org.hamcrest.core.Is.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@WebMvcTest(controllers = ParentController.class)
@RunWith(SpringRunner.class)
public class ParentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @MockBean
    private ParentController parentController;

    @Test
    public void whenCallParentsList_thenReturns200() throws Exception {
        mockMvc.perform(get("/api/parents"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenGettingIndividualParent_givenNotExistingParentId_thenReturns400() throws Exception {
        mockMvc.perform(get("/api/parents/123123123"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void whenRequestingAllParentsEndpoint_thenReturnListOfParents() throws Exception {
        Parent parent = new Parent();
        parent.setFirstname("John");
        parent.setLastname("Doe");
        parent.setParentType("Father");
        parent.setPhone("111111111");
        List<Parent> parentList = List.of(parent);
        List<ParentViewDto> parentViewDtos = Transformer.mapAll(parentList,ParentViewDto.class);
        given(parentController.getAll()).willReturn(ResponseEntity.ok(parentViewDtos));
        mockMvc.perform(get("/api/parents").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].firstname", is(parent.getFirstname())));
    }

    @Test
    public void whenRequestingIndividualParentEndpoint_thenReturnParent() throws Exception {
        Parent parent = new Parent();
        parent.setId("1231238102u3182903871");
        parent.setFirstname("John");
        parent.setLastname("Doe");
        parent.setParentType("Father");
        parent.setPhone("111111111");
        ParentViewDto parentViewDto = Transformer.map(parent,ParentViewDto.class);
        given(parentController.getById("1231238102u3182903871")).willReturn(ResponseEntity.ok(parentViewDto));
        mockMvc.perform(get("/api/parents/1231238102u3182903871" ).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstname", is(parent.getFirstname())));
    }
}

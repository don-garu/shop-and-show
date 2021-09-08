package com.example.shopandshow.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.shopandshow.persistence.dto.UserDTO;
import com.example.shopandshow.persistence.model.Gender;
import com.example.shopandshow.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.RequestParametersSnippet;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(UserController.class)
@AutoConfigureRestDocs
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("사용자 계정 생성")
    public void create() throws Exception {
        // Given
        when(userService.create(any()))
            .thenReturn(aResultDTO());

        // When
        String body = objectMapper.writeValueAsString(aCreateDTO());

        ResultActions actions = mockMvc.perform(
            RestDocumentationRequestBuilders.post("/user/create")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        actions.andExpect(status().isCreated())
            .andDo(document("user/create",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                createRequestFields(),
                commonResponseFields()));
    }

    @Test
    @DisplayName("사용자 계정 로그인")
    public void login() throws Exception {
        // Given
        when(userService.login(any(), any()))
            .thenReturn(aResultDTO());

        // When
        ResultActions actions = mockMvc.perform(
            RestDocumentationRequestBuilders.get("/user/login")
                .param("name", "user")
                .param("password", "password"));

        // Then
        actions.andExpect(status().isOk())
            .andDo(document("user/login",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                loginRequestParams(),
                commonResponseFields()));
    }

    private RequestParametersSnippet loginRequestParams() {
        return requestParameters(
            parameterWithName("name").description("사용자명"),
            parameterWithName("password").description("비밀번호"));
    }

    private RequestFieldsSnippet createRequestFields() {
        return requestFields(
            fieldWithPath("name").type(JsonFieldType.STRING).description("사용자명"),
            fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호"),
            fieldWithPath("address").type(JsonFieldType.STRING).description("주소"),
            fieldWithPath("age").type(JsonFieldType.NUMBER).description("나이"),
            fieldWithPath("gender").type(JsonFieldType.STRING).description("성별"));
    }

    private ResponseFieldsSnippet commonResponseFields() {
        return responseFields(
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("계정 식별자"),
            fieldWithPath("name").type(JsonFieldType.STRING).description("사용자명"),
            fieldWithPath("address").type(JsonFieldType.STRING).description("주소"),
            fieldWithPath("age").type(JsonFieldType.NUMBER).description("나이"),
            fieldWithPath("gender").type(JsonFieldType.STRING).description("성별"),
            fieldWithPath("purchasedItems").type(JsonFieldType.ARRAY).description("구매한 상품 목록"));
    }

    private UserDTO.Result aResultDTO() {
        return UserDTO.Result.builder()
            .id(1)
            .age(10)
            .name("user")
            .address("user-address")
            .gender(Gender.MALE)
            .purchasedItems(new LinkedList<>())
            .build();
    }

    private UserDTO.Create aCreateDTO() {
        return UserDTO.Create.builder()
            .age(10)
            .name("user")
            .password("password")
            .address("user-address")
            .gender(Gender.MALE)
            .build();
    }

}
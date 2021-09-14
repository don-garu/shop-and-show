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
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.shopandshow.persistence.dto.PostDTO;
import com.example.shopandshow.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
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
import org.springframework.restdocs.request.PathParametersSnippet;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(PostController.class)
@AutoConfigureRestDocs
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostService postService;

    @Test
    @DisplayName("게시물 생성")
    void create() throws Exception {
        // Given
        when(postService.create(any()))
            .thenReturn(aResultDTO());

        // When
        String body = objectMapper.writeValueAsString(aCreateDTO());
        ResultActions actions = mockMvc.perform(
            RestDocumentationRequestBuilders.post("/post/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));

        // Then
        actions.andExpect(status().isCreated())
            .andDo(document("post/create",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                createRequestFields(),
                commonResponseFields()));
    }

    @Test
    void read() throws Exception {
        // Given
        when(postService.read(any()))
            .thenReturn(aResultDTO());

        // When
        ResultActions actions = mockMvc.perform(
            RestDocumentationRequestBuilders.get("/post/{id}", 2));

        // Then
        actions.andExpect(status().isOk())
            .andDo(document("post/read",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                readPathParams(),
                commonResponseFields()));
    }

    @Test
    void readAll() throws Exception {
        // Given
        when(postService.readAll(any()))
            .thenReturn(List.of(aResultDTO()));

        // When
        ResultActions actions = mockMvc.perform(
            RestDocumentationRequestBuilders.get("/post/user/{userId}", 1));

        // Then
        actions.andExpect(status().isOk())
            .andDo(document("post/readAll",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                readAllPathParams(),
                readAllResponseFields()));
    }

    @Test
    void update() throws Exception {
        // Given
        when(postService.update(any()))
            .thenReturn(aResultDTO());

        // When
        String body = objectMapper.writeValueAsString(aUpdateDTO());
        ResultActions actions = mockMvc.perform(
            RestDocumentationRequestBuilders.put("/post/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));

        // Then
        actions.andExpect(status().isOk())
            .andDo(document("post/update",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                updateRequestFields(),
                commonResponseFields()));
    }

    private PathParametersSnippet readPathParams() {
        return pathParameters(
            parameterWithName("id").description("게시물 식별자"));
    }

    private PathParametersSnippet readAllPathParams() {
        return pathParameters(
            parameterWithName("userId").description("사용자 계정 식별자"));
    }

    private RequestFieldsSnippet updateRequestFields() {
        return requestFields(
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("게시물 식별자"),
            fieldWithPath("imagePath").type(JsonFieldType.STRING).description("게시물 이미지 주소")
                .optional(),
            fieldWithPath("body").type(JsonFieldType.STRING).description("게시물 내용").optional());
    }

    private RequestFieldsSnippet createRequestFields() {
        return requestFields(
            fieldWithPath("userId").type(JsonFieldType.NUMBER).description("사용자 계정 식별자"),
            fieldWithPath("imagePath").type(JsonFieldType.STRING).description("게시물 이미지 주소"),
            fieldWithPath("body").type(JsonFieldType.STRING).description("게시물 내용"));
    }

    private ResponseFieldsSnippet commonResponseFields() {
        return responseFields(
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("게시물 식별자"),
            fieldWithPath("userId").type(JsonFieldType.NUMBER).description("사용자 계정 식별자"),
            fieldWithPath("imagePath").type(JsonFieldType.STRING).description("게시물 이미지 주소"),
            fieldWithPath("body").type(JsonFieldType.STRING).description("게시물 내용"));
    }

    private ResponseFieldsSnippet readAllResponseFields() {
        return responseFields(
            fieldWithPath("[]").type(JsonFieldType.ARRAY).description("게시물 리스트"),
            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("게시물 식별자"),
            fieldWithPath("[].userId").type(JsonFieldType.NUMBER).description("사용자 계정 식별자"),
            fieldWithPath("[].imagePath").type(JsonFieldType.STRING).description("게시물 이미지 주소"),
            fieldWithPath("[].body").type(JsonFieldType.STRING).description("게시물 내용")
        );
    }

    private PostDTO.Create aCreateDTO() {
        return PostDTO.Create.builder()
            .userId(1)
            .imagePath("Image File Path")
            .body("Post Body")
            .build();
    }

    private PostDTO.Update aUpdateDTO() {
        return PostDTO.Update.builder()
            .id(2)
            .imagePath("New Image File Path")
            .body("New Post Body")
            .build();
    }

    private PostDTO.Result aResultDTO() {
        return PostDTO.Result.builder()
            .id(2)
            .userId(1)
            .imagePath("Image File Path")
            .body("Post Body")
            .build();
    }
}
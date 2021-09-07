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

import com.example.shopandshow.persistence.dto.ItemDTO;
import com.example.shopandshow.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@WebMvcTest(ItemController.class)
@AutoConfigureRestDocs
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ItemService itemService;

    @Test
    @DisplayName("상품 생성")
    public void create() throws Exception {
        // Given
        when(itemService.create(any()))
            .thenReturn(aResultDTO());

        // When
        String body = objectMapper.writeValueAsString(aCreateDTO());
        ResultActions actions = mockMvc.perform(
            RestDocumentationRequestBuilders.post("/item/create")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        actions.andExpect(status().isCreated())
            .andDo(document("item/create",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                createRequestFields(),
                commonResponseFields()));
    }

    @Test
    @DisplayName("상품 조회")
    public void read() throws Exception {
        // Given
        when(itemService.read(any()))
            .thenReturn(aResultDTO());

        // When
        ResultActions actions = mockMvc.perform(
            RestDocumentationRequestBuilders.get("/item/{id}", 2));

        // Then
        actions.andExpect(status().isOk())
            .andDo(document("item/read",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                readPathParams(),
                commonResponseFields()));
    }

    @Test
    @DisplayName("상품 수정")
    public void update() throws Exception {
        // Given
        when(itemService.update(any()))
            .thenReturn(aResultDTO());

        // When
        String body = objectMapper.writeValueAsString(aUpdateDTO());
        ResultActions actions = mockMvc.perform(
            RestDocumentationRequestBuilders.put("/item/update")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        actions.andExpect(status().isOk())
            .andDo(document("item/update",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                updateRequestFields(),
                commonResponseFields()));
    }

    private PathParametersSnippet readPathParams() {
        return pathParameters(
            parameterWithName("id").description("상품 식별자"));
    }

    private RequestFieldsSnippet updateRequestFields() {
        return requestFields(
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("상품 식별자"),
            fieldWithPath("price").type(JsonFieldType.NUMBER).description("상품 가격").optional(),
            fieldWithPath("remainingQuantity").type(JsonFieldType.NUMBER).description("상품 수량")
                .optional(),
            fieldWithPath("bannerImagePath").type(JsonFieldType.STRING).description("상품 배너 이미지 주소")
                .optional(),
            fieldWithPath("descriptionImagePath").type(JsonFieldType.STRING)
                .description("상품 설명 이미지 주소").optional());
    }

    private RequestFieldsSnippet createRequestFields() {
        return requestFields(
            fieldWithPath("merchantId").type(JsonFieldType.NUMBER).description("판매자 계정 식별자"),
            fieldWithPath("price").type(JsonFieldType.NUMBER).description("상품 가격"),
            fieldWithPath("quantity").type(JsonFieldType.NUMBER).description("상품 수량"),
            fieldWithPath("name").type(JsonFieldType.STRING).description("상품명"));
    }

    private ResponseFieldsSnippet commonResponseFields() {
        return responseFields(
            fieldWithPath("id").type(JsonFieldType.NUMBER).description("상품 식별자"),
            fieldWithPath("price").type(JsonFieldType.NUMBER).description("상품 가격"),
            fieldWithPath("remainingQuantity").type(JsonFieldType.NUMBER).description("상품 잔여 수량"),
            fieldWithPath("name").type(JsonFieldType.STRING).description("상품명"),
            fieldWithPath("bannerImagePath").type(JsonFieldType.STRING).description("상품 배너 이미지 주소"),
            fieldWithPath("descriptionImagePath").type(JsonFieldType.STRING)
                .description("상품 설명 이미지 주소"));
    }

    private ItemDTO.Create aCreateDTO() {
        return ItemDTO.Create.builder()
            .merchantId(1)
            .price(100)
            .quantity(5000)
            .name("item")
            .build();
    }

    private ItemDTO.Update aUpdateDTO() {
        return ItemDTO.Update.builder()
            .id(2)
            .price(300)
            .remainingQuantity(10000)
            .bannerImagePath("bannerImage")
            .descriptionImagePath("descriptionImage")
            .build();
    }

    private ItemDTO.Result aResultDTO() {
        return ItemDTO.Result.builder()
            .id(2)
            .price(100)
            .remainingQuantity(5000)
            .name("item")
            .bannerImagePath("bannerImage")
            .descriptionImagePath("descriptionImage")
            .build();
    }
}
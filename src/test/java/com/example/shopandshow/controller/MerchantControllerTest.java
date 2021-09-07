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

import com.example.shopandshow.persistence.dto.MerchantDTO;
import com.example.shopandshow.service.MerchantService;
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
import org.springframework.restdocs.request.RequestParametersSnippet;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(MerchantController.class)
@AutoConfigureRestDocs
class MerchantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MerchantService merchantService;

    @Test
    @DisplayName("판매자 계정 생성")
    void create() throws Exception {
        // Given
        when(merchantService.create(any()))
            .thenReturn(aResultDTO());

        // When
        String body = objectMapper.writeValueAsString(aCreateDTO());

        ResultActions actions = mockMvc.perform(
            RestDocumentationRequestBuilders.post("/merchant/create")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        actions.andExpect(status().isCreated())
            .andDo(document("merchant/create",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                createRequestFields(),
                commonResponseFields()
            ));
    }

    @Test
    @DisplayName("판매자 계정 로그인")
    void login() throws Exception {
        // Given
        when(merchantService.login(any()))
            .thenReturn(aResultDTO());

        // When
        ResultActions actions = mockMvc.perform(
            RestDocumentationRequestBuilders.get("/merchant/login")
                .param("userId", "1"));

        // Then
        actions.andExpect(status().isOk())
            .andDo(document("merchant/login",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                loginRequestParams(),
                commonResponseFields()
            ));
    }

    private RequestParametersSnippet loginRequestParams() {
        return requestParameters(
            parameterWithName("userId").description("사용자 계정 식별자")
        );
    }

    private RequestFieldsSnippet createRequestFields() {
        return requestFields(
            fieldWithPath("userId").type(JsonFieldType.NUMBER).description("사용자 계정 식별자"),
            fieldWithPath("wallet").type(JsonFieldType.NUMBER).description("판매자 계좌 초기 금액")
        );
    }

    private ResponseFieldsSnippet commonResponseFields() {
        return responseFields(
            fieldWithPath("id").description("계정 식별자"),
            fieldWithPath("userId").description("사용자 계정 식별자"),
            fieldWithPath("wallet").description("판매자 계좌 잔액")
        );
    }

    private MerchantDTO.Create aCreateDTO() {
        return MerchantDTO.Create.builder()
            .userId(1)
            .wallet(1000)
            .build();
    }

    private MerchantDTO.Result aResultDTO() {
        return MerchantDTO.Result.builder()
            .id(0)
            .userId(1)
            .wallet(1000)
            .build();
    }
}
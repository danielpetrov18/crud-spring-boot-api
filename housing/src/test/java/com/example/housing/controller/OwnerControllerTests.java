package com.example.housing.controller;

import com.example.housing.domain.entity.Owner;
import com.example.housing.service.OwnerService;
import com.example.housing.utility.OwnerBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode =  DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OwnerControllerTests {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final OwnerService ownerService;

    @Autowired
    public OwnerControllerTests(MockMvc newMockMvc, OwnerService newOwnerService, ObjectMapper newObjectMapper) {
        this.mockMvc = newMockMvc;
        this.objectMapper = newObjectMapper;
        this.ownerService = newOwnerService;
    }

    @Test
    public void testPostMethodOwnerCreatedSuccessfullyReturns201StatusCode() throws Exception {
        final Owner createdOwner = OwnerBuilder.getOwnerC();

        final String ownerJson = this.objectMapper.writeValueAsString(createdOwner);

        this.mockMvc.perform(
            MockMvcRequestBuilders
                    .post("/owners")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(ownerJson)
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.firstname").value(createdOwner.getFirstname()))
        .andExpect(jsonPath("$.lastname").value(createdOwner.getLastname()))
        .andExpect(jsonPath("$.birthDate").value(createdOwner.getBirthDate().toString()));
    }

    @Test
    public void testGetMethodWithExistingOwnerIdReturns200StatusCode() throws Exception {
        final Owner savedOwner = this.ownerService.saveOwner(OwnerBuilder.getOwnerC());
        final Long existingOwnerId = 1L;

        this.mockMvc.perform(
            MockMvcRequestBuilders
                    .get("/owners/{id}", existingOwnerId)
                    .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.ownerId").value(savedOwner.getOwnerId()))
        .andExpect(jsonPath("$.firstname").value(savedOwner.getFirstname()))
        .andExpect(jsonPath("$.lastname").value(savedOwner.getLastname()))
        .andExpect(jsonPath("$.birthDate").value(savedOwner.getBirthDate().toString()));
    }

    @Test
    public void testPutMethodWithNonExistingOwnerIdReturns404StatusCode() throws Exception {
        final Long nonExistingOwnerId = 42L;
        final Owner owner = OwnerBuilder.getOwnerB();
        final String ownerJson = this.objectMapper.writeValueAsString(owner);

        this.mockMvc.perform(
            MockMvcRequestBuilders
                    .put("/owners/{id}", nonExistingOwnerId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(ownerJson)
        ).andExpect(status().isNotFound());
    }

    @Test
    public void testPutMethodWithExistingOwnerIdReturns200StatusCode() throws Exception {
        this.ownerService.saveOwner(OwnerBuilder.getOwnerC());

        final Long existingOwnerId = 1L;
        final Owner newOwner = OwnerBuilder.getOwnerA();
        newOwner.setOwnerId(existingOwnerId);
        final String newOwnerJson = this.objectMapper.writeValueAsString(newOwner);

        this.mockMvc.perform(
            MockMvcRequestBuilders
                    .put("/owners/{id}", existingOwnerId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(newOwnerJson)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.ownerId").value(existingOwnerId))
        .andExpect(jsonPath("$.firstname").value(newOwner.getFirstname()))
        .andExpect(jsonPath("$.lastname").value(newOwner.getLastname()))
        .andExpect(jsonPath("$.birthDate").value(newOwner.getBirthDate().toString()));
    }

    @Test
    public void testExistingOwnerDeletedSuccessfullyReturns200StatusCode() throws Exception {
        final Owner toBeDeleted = this.ownerService.saveOwner(OwnerBuilder.getOwnerC());

        final Long existingOwnerId = 1L;

        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/owners/{id}", existingOwnerId)
                        .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.ownerId").value(toBeDeleted.getOwnerId()))
        .andExpect(jsonPath("$.firstname").value(toBeDeleted.getFirstname()))
        .andExpect(jsonPath("$.lastname").value(toBeDeleted.getLastname()))
        .andExpect(jsonPath("$.birthDate").value(toBeDeleted.getBirthDate().toString()));
    }

}
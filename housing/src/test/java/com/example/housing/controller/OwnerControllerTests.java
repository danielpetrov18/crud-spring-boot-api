package com.example.housing.controller;

import lombok.extern.slf4j.Slf4j;

import com.example.housing.domain.entity.Owner;
import com.example.housing.service.OwnerService;
import com.example.housing.utility.OwnerBuilder;

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

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode =  DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OwnerControllerTests {

    private final MockMvc mockMvc;
    private final OwnerService ownerService;

    @Autowired
    public OwnerControllerTests(final MockMvc newMockMvc, final OwnerService newOwnerService) {
        this.mockMvc = newMockMvc;
        this.ownerService = newOwnerService;
    }

    @Test
    public void testPostMethodOwnerCreatedSuccessfullyReturns201StatusCode() {
        final String newOwnerJson = """
                        {
                            "ownerId": 1,
                            "firstname": "John",
                            "lastname": "Snow",
                            "birthDate": "1990-01-01",
                            "estates": []
                        }""";

        try {
            this.mockMvc.perform(
                            MockMvcRequestBuilders
                                    .post("/owners")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(newOwnerJson)
                    )
                    .andExpect(status().isCreated());
        }
        catch(Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testGetMethodWithExistingOwnerIdReturns200StatusCode() {
        final Owner savedOwner = this.ownerService.createOwner(OwnerBuilder.getOwnerC());
        final Long existingOwnerId = 1L;

        try {
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
        catch(Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testPutMethodWithNonExistingOwnerIdReturns404StatusCode() {
        final Long nonExistingOwnerId = 42L;
        final String newOwnerJson = """
                        {
                            "ownerId": 42,
                            "firstname": "John",
                            "lastname": "Snow",
                            "birthDate": "1990-01-01",
                            "estates": []
                        }""";

        try {
            this.mockMvc.perform(
                    MockMvcRequestBuilders
                            .put("/owners/{id}", nonExistingOwnerId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(newOwnerJson)
            ).andExpect(status().isNotFound());
        }
        catch(Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testPutMethodWithExistingOwnerIdReturns200StatusCode() {
        this.ownerService.createOwner(OwnerBuilder.getOwnerC());

        final Long existingOwnerId = 1L;
        final String updatedOwnerJson = """
                        {
                            "ownerId": 1,
                            "firstname": "John",
                            "lastname": "Snow",
                            "birthDate": "1990-01-01",
                            "estates": []
                        }""";

        try {
            this.mockMvc.perform(
                    MockMvcRequestBuilders
                            .put("/owners/{id}", existingOwnerId)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(updatedOwnerJson)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.ownerId").value(existingOwnerId))
            .andExpect(jsonPath("$.firstname").value("John"))
            .andExpect(jsonPath("$.lastname").value("Snow"))
            .andExpect(jsonPath("$.birthDate").value("1990-01-01"));
        }
        catch(Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void testExistingOwnerDeletedSuccessfullyReturns200StatusCode() {
        final Owner toBeDeleted = this.ownerService.createOwner(OwnerBuilder.getOwnerC());

        final Long existingOwnerId = 1L;

        try {
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
        catch(Exception e) {
            log.error(e.getMessage());
        }
    }

}
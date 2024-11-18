package com.example.iwa_ms_admin_moderation;

import com.example.iwa_ms_admin_moderation.models.Flags;
import com.example.iwa_ms_admin_moderation.services.FlagService;
import com.example.iwa_ms_admin_moderation.controllers.FlagController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FlagController.class)
public class FlagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlagService flagService;

    private Flags testFlag;

    @BeforeEach
    void setUp() {
        testFlag = new Flags();
        testFlag.setFlagId(1);
        testFlag.setLocationId(101);
        testFlag.setUserId(201);
        testFlag.setReason("Inappropriate content");
        testFlag.setStatus("pending");
    }

    @Test
    void testCreateFlag() throws Exception {
        Mockito.when(flagService.createFlag(any(Flags.class)))
                .thenReturn(testFlag);

        String jsonRequest = """
            {
                "locationId": 101,
                "userId": 201,
                "reason": "Inappropriate content",
                "status": "pending"
            }
            """;

        mockMvc.perform(post("/flags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.flagId").value(1))
                .andExpect(jsonPath("$.locationId").value(101))
                .andExpect(jsonPath("$.userId").value(201))
                .andExpect(jsonPath("$.reason").value("Inappropriate content"))
                .andExpect(jsonPath("$.status").value("pending"));
    }

    @Test
    void testGetAllFlags() throws Exception {
        Mockito.when(flagService.getAllFlags())
                .thenReturn(Arrays.asList(testFlag));

        mockMvc.perform(get("/flags"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].flagId").value(1))
                .andExpect(jsonPath("$[0].locationId").value(101))
                .andExpect(jsonPath("$[0].userId").value(201))
                .andExpect(jsonPath("$[0].reason").value("Inappropriate content"))
                .andExpect(jsonPath("$[0].status").value("pending"));
    }

    @Test
    void testGetFlagById() throws Exception {
        Mockito.when(flagService.getFlagById(1))
                .thenReturn(Optional.of(testFlag));

        mockMvc.perform(get("/flags/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flagId").value(1))
                .andExpect(jsonPath("$.locationId").value(101))
                .andExpect(jsonPath("$.userId").value(201))
                .andExpect(jsonPath("$.reason").value("Inappropriate content"))
                .andExpect(jsonPath("$.status").value("pending"));
    }

    @Test
    void testGetFlagById_NotFound() throws Exception {
        Mockito.when(flagService.getFlagById(99))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/flags/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateFlag() throws Exception {
        testFlag.setStatus("resolved");
        Mockito.when(flagService.updateFlag(any(Flags.class)))
                .thenReturn(testFlag);
        Mockito.when(flagService.getFlagById(1))
                .thenReturn(Optional.of(testFlag));

        String jsonRequest = """
            {
                "locationId": 101,
                "userId": 201,
                "reason": "Inappropriate content",
                "status": "resolved"
            }
            """;

        mockMvc.perform(put("/flags/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flagId").value(1))
                .andExpect(jsonPath("$.status").value("resolved"));
    }

    @Test
    void testDeleteFlag() throws Exception {
        Mockito.when(flagService.getFlagById(1))
                .thenReturn(Optional.of(testFlag));

        mockMvc.perform(delete("/flags/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetFlagsByStatus() throws Exception {
        Mockito.when(flagService.getFlagsByStatus("pending"))
                .thenReturn(Arrays.asList(testFlag));

        mockMvc.perform(get("/flags/status/pending"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].flagId").value(1))
                .andExpect(jsonPath("$[0].status").value("pending"));
    }
}

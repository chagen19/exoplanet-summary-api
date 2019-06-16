package com.chagen.exoplanet.summary.api.controller

import com.chagen.exoplanet.summary.api.facade.SummaryFacade
import com.chagen.exoplanet.summary.api.model.Summary
import com.chagen.exoplanet.summary.api.model.TimelineEntry
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*


/**
 * Tests for SummaryController
 * @author Chad Hagen
 */
@WebMvcTest
class SummaryControllerTest(@Autowired val mockMvc: MockMvc) {
    @MockkBean
    private lateinit var summaryFacade: SummaryFacade

    @Test
    fun `should create summary`() {
        val timelineEntries = listOf(
                TimelineEntry(2015, 1, 2, 3),
                TimelineEntry(2016, 10, 5, 1),
                TimelineEntry(2017, 5, 0, 0),
                TimelineEntry(2018, 4, 2, 7),
                TimelineEntry(2019, 1, 0, 11)
        )
        every { summaryFacade.buildSummary() } returns Summary(3, "hottestPlanet", timelineEntries)

        mockMvc.perform(get("/exoplanetSummary").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("\$.nameOfPlanetOrbitingHottestStar").value("hottestPlanet"))
                .andExpect(jsonPath("\$.numberOfOrphanedPlanets").value(3))
                .andExpect(jsonPath("\$.timeline[0].year").value(2015))
                .andExpect(jsonPath("\$.timeline[0].largePlanets").value(3))
                .andExpect(jsonPath("\$.timeline[1].year").value(2016))
                .andExpect(jsonPath("\$.timeline[1].mediumPlanets").value(5))
                .andExpect(jsonPath("\$.timeline[2].year").value(2017))
                .andExpect(jsonPath("\$.timeline[2].smallPlanets").value(5))
    }

}
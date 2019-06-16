package com.chagen.exoplanet.summary.api.client.impl

import com.chagen.exoplanet.summary.api.client.CatalogueClient
import com.chagen.exoplanet.summary.api.exception.CatalogueClientException
import com.chagen.exoplanet.summary.api.model.Exoplanet
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers
import org.springframework.test.web.client.response.MockRestResponseCreators
import java.net.SocketTimeoutException


/**
 * Tests for SummaryController
 * @author Chad Hagen
 */
@RestClientTest(CatalogueClient::class)
@TestPropertySource(properties = arrayOf("exoplanet.catalogue.api.url=http://localhost:8080"))
class CatalogueClientImplTest(@Autowired val mockServer: MockRestServiceServer, @Autowired val catalogueClient: CatalogueClient, @Autowired val objectMapper: ObjectMapper) {
    val exoplanets = listOf(
            Exoplanet("Planet-1", 3, 1.2, 0, -9000 ),
            Exoplanet("Planet-2", 3, 0.524, 2010, 2500 ),
            Exoplanet("Planet-3", 1, .0123, 2004, 7050 )
    )

    @Test
    fun `should retrieve extoplanets`() {
        val response = objectMapper.writeValueAsString(exoplanets);
        mockServer.expect(MockRestRequestMatchers.requestTo("/exoplanets"))
                .andRespond(MockRestResponseCreators.withSuccess(response, MediaType.APPLICATION_JSON_UTF8));

        val catalogue = catalogueClient.catalogue
        assertEquals (3, catalogue.size)
    }

    @Test
    fun `should throw CatalogueClientException when bad response code is returned from service`() {
        mockServer.expect(MockRestRequestMatchers.requestTo("/exoplanets"))
                .andRespond(MockRestResponseCreators.withServerError());

        assertThrows(CatalogueClientException::class.java) { catalogueClient.catalogue }
    }

    @Test
    fun `should throw CatalogueClientException when exception is thrown calling service`() {
        mockServer.expect(MockRestRequestMatchers.requestTo("/exoplanets"))
                .andRespond { throw SocketTimeoutException("timeout") };
        assertThrows(CatalogueClientException::class.java) { catalogueClient.catalogue }
    }
}
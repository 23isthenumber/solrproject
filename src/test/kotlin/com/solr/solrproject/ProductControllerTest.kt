package com.solr.solrproject

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.io.File

@SpringBootTest
@AutoConfigureMockMvc
@Import(SolrConfig::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ProductControllerTest(@Autowired val mockMvc: MockMvc) {

    private val BASE_URL : String = "/api/product"
    private val objectMapper: ObjectMapper = ObjectMapper()
    private val save_response : String = File("./src/test/resources/save_response.json").readText()
    private val update_response : String = File("./src/test/resources/update_response.json").readText()
    private val findByName_response : String = File("./src/test/resources/findByName_response.json").readText()
    private val query_response : String = File("./src/test/resources/query_response.json").readText()

    @AfterAll
    fun resetSolr() {
        mockMvc.perform(
            delete(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
    }

    @Test
    @Order(1)
    fun save() {
        //given:
        val product = Product().apply {
            id = "1"
            name = "name1"
        }

        //when:
        mockMvc.perform(
            post(BASE_URL)
                .content(objectMapper.writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            //then:
            .andExpect(status().isOk)
            .andExpect(content().json(save_response))
    }

    @Test
    @Order(2)
    fun update() {
        //given:
        val product = Product().apply {
            id = "1"
            name = "nameUpdated"
        }

        //when:
        mockMvc.perform(
            post(BASE_URL)
                .content(objectMapper.writeValueAsString(product))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            //then:
            .andExpect(status().isOk)
            .andExpect(content().json(update_response))
    }

    @Test
    @Order(3)
    fun findByName() {
        //given:
        val name = "nameUpdated"

        //when:
        mockMvc.perform(
            get("$BASE_URL/name/$name")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            //then:
            .andExpect(status().isOk)
            .andExpect(content().json(findByName_response))
    }

    @Test
    @Order(5)
    fun findById() {
        //given:
        val id = "1"

        //when:
        mockMvc.perform(
            get("$BASE_URL/id/$id")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            //then:
            .andExpect(status().isOk)
            .andExpect(content().json(update_response))
    }

    @Test
    @Order(6)
    fun findByCustomQuery() {
        //given:
        val searchTerm = "1"

        //when:
        mockMvc.perform(
            get("$BASE_URL/custom/$searchTerm")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            //then:
            .andExpect(status().isOk)
            .andExpect(content().json(query_response))
    }

    @Test
    @Order(7)
    fun findByNamedQuery() {
        //given:
        val searchTerm = "1"

        //when:
        mockMvc.perform(
            get("$BASE_URL/named/$searchTerm")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            //then:
            .andExpect(status().isOk)
            .andExpect(content().json(query_response))
    }
}
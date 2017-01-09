package com.chicago.labs.humans

import com.chicago.labs.domain.Human
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class HumansControllerTest {

    lateinit var humansService: HumansService
    lateinit var humansController: HumansController
    lateinit var mockMvc: MockMvc

    @Before
    fun setUp() {
        humansService = mock()
        humansController = HumansController(humansService
        )
        mockMvc = MockMvcBuilders.standaloneSetup(humansController).build()
    }

    @Test
    fun `has a POST endpoint`() {
        mockMvc.perform(post("/humans")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ " +
                        "\"name\": \"Boop\"," +
                        "\"email\": \"boop@beep.com\"" +
                        "}"))
                .andExpect(status().isCreated)
    }

    @Test
    fun `has a GET endpoint`() {
        doReturn(listOf(Human(name = "hi", email = "yo"))).whenever(humansService).getAll()
        mockMvc.perform(get("/humans")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty)
                .andExpect(status().isOk)
    }

    @Test
    fun `has a DELETE endpoint to deactivate`(){
        mockMvc.perform(delete("/humans")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ " +
                        "\"name\": \"Boop\"," +
                        "\"email\": \"boop@beep.com\"" +
                        "}"))
                .andExpect(status().isOk)
    }

    @Test
    fun `calls service to create on POST`() {
        val human = Human(name = "Bobs Burgers", email = "bob@burgers.com")
        humansController.post(human)

        verify(humansService).create(human)
    }

    @Test
    fun `calls service to get all on GET`() {
        doReturn(listOf(Human(name = "wat", email = "yah"))).whenever(humansService).getAll()
        val humans = humansController.get()
        assertThat(humans).isEqualTo(listOf(Human(name = "wat", email = "yah")))
        verify(humansService).getAll()
    }

    @Test
    fun `calls service on DELETE`() {
        humansController.delete(Human(null, "", ""))

        verify(humansService).delete(Human(null, "", ""))
    }
}
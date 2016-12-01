package com.chicago.labs.pair

import com.chicago.labs.humans.Human
import com.chicago.labs.humans.PairController
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class PairControllerTest {

    lateinit var pairService: PairService
    lateinit var pairController: PairController
    lateinit var mockMvc: MockMvc

    @Before
    fun setUp() {
        pairService = mock()
        pairController = PairController(pairService)
        mockMvc = MockMvcBuilders.standaloneSetup(pairController).build()
    }

    @Test
    fun `has a GET endpoint`() {
        doReturn(listOf(Pair(Human(), Human()))).whenever(pairService).match()
        mockMvc.perform(get("/pair")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty)
                .andExpect(status().isOk)
    }

    @Test
    fun `calls service to get all on GET`() {
        doReturn(listOf(Pair(Human(), Human()))).whenever(pairService).match()
        val humans = pairController.get()
        assertThat(humans).isEqualTo(listOf(Pair(Human(), Human())))
        verify(pairService).match()
    }
}
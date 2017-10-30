package com.chicago.labs.pair

import com.chicago.labs.domain.Human
import com.chicago.labs.domain.Pair
import com.chicago.labs.domain.PairingList
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.time.LocalDateTime

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
    fun `should have a POST endpoint to match and record pairs`() {
        mockMvc.perform(post("/pair/matchAndRecord")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)

        verify(pairService).matchAndRecord()
    }

    @Test
    fun `should return pairing list after match and record`(){
        val expectedPairingList = PairingList()
        doReturn(expectedPairingList).whenever(pairService).matchAndRecord()
        val actualPairingList = pairController.matchAndRecord()
        assertThat(actualPairingList).isEqualTo(expectedPairingList)
    }

    @Test
    fun `has a latest pair GET endpoint`() {
        doReturn(PairingList(null, listOf(
                Pair(Human("", ""), Human("", ""))),
                LocalDateTime.now()))
                .whenever(pairService).latest()
        mockMvc.perform(get("/pair/latest")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty)
                .andExpect(status().isOk)

        verify(pairService).latest()
    }

    @Test
    fun `calls service to get latest pairing list`() {
        val expectedPairingList = PairingList(null, listOf(
                Pair(Human("", ""), Human("", ""))),
                LocalDateTime.now())
        doReturn(expectedPairingList).whenever(pairService).latest()
        val humans = pairController.getLatest()
        assertThat(humans).isEqualTo(expectedPairingList)
        verify(pairService).latest()
    }
}
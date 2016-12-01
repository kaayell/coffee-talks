package com.chicago.labs.humans

import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class HumansServiceTest {

    lateinit var humanRepository: HumanRepository
    lateinit var humansService: HumansService

    @Before
    fun setUp() {
        humanRepository = mock()
        humansService = HumansService(humanRepository)
    }

    @Test
    fun `saves human to repo`() {
        val human = Human(email = "yo@hi.com", name = "hay")
        humansService.create(human)

        verify(humanRepository).save(human)
    }

    @Test
    fun `gets all humans`() {
        doReturn(listOf(Human())).whenever(humanRepository).findAll()
        val humans = humansService.getAll()
        assertThat(humans).isEqualTo(listOf(Human()))
    }
}
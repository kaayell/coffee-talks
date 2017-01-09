package com.chicago.labs.humans

import com.chicago.labs.domain.Human
import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.bson.types.ObjectId
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor

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
        doReturn(listOf(Human(email = "", name = ""))).whenever(humanRepository).findAll()
        val humans = humansService.getAll()
        assertThat(humans).isEqualTo(listOf(Human(email = "", name = "")))
    }

    @Test
    fun `gets human from repo and deletes them`() {
        doReturn(Human(email = "email", name = ""))
                .whenever(humanRepository).findFirstByEmail("email")
        humansService.delete(Human(email = "email"))

        verify(humanRepository).delete(Human(email = "email", name = ""))
    }
}
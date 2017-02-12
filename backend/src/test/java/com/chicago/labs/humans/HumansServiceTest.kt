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
        val human = Human("yo@hi.com", "hay")
        humansService.create(human)

        verify(humanRepository).save(human)
    }

    @Test
    fun `gets all humans`() {
        doReturn(listOf(Human("", ""))).whenever(humanRepository).findAll()
        val humans = humansService.getAll()
        assertThat(humans).isEqualTo(listOf(Human("", "")))
    }

    @Test
    fun `gets all humans emails`() {
        doReturn(listOf(Human("email1", "human1"), Human("email2", "human2"))).whenever(humanRepository).findAll()
        val emails = humansService.getEmails()
        assertThat(emails).isEqualTo(listOf("email1", "email2"))
    }

    @Test
    fun `gets human from repo and deletes them`() {
        humansService.remove(Human("email", ""))

        verify(humanRepository).deleteHumanByEmail("email")
    }
}
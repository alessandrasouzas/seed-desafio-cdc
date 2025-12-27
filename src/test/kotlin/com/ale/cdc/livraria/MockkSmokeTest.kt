package com.ale.cdc.livraria

import io.mockk.mockk
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class MockkSmokeTest {

    @Test
    fun `mockk funciona`() {
        val obj = mockk<Any>()
        assertNotNull(obj)
    }
}

package com.example.mytest

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import java.lang.IllegalArgumentException

class ExampleUnitTest {

    //Математические выражения
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun subtraction_isCorrect() {
        assertEquals(0, 2 - 2)
    }

    @Test
    fun multiplication_isCorrect() {
        assertEquals(6, 2 * 3)
    }

    @Test
    fun division_isCorrect() {
        assertEquals(2, 6 / 3)
    }

    //  Получение данных с сервера
    @Test
    fun fetchData_success() = runBlocking {
        val mockApi = MainActivity.MockApi(nullResponse = true)
        val data = mockApi.fetchData()
        assertNotNull(data)
        assertEquals("Mocked Data", data)
    }
    @Test(expected = Exception::class)
    fun fetchData_failure() = runBlocking {
        val mockApi = MainActivity.MockApi(false, true)
        val data = mockApi.fetchData()
    }

    @Test
    fun fetchData_emptyResponse() = runBlocking {
        val mockApi = MainActivity.MockApi(emptyResponse = true)
        val data = mockApi.fetchData()
        assertEquals("", data)
    }

    //  Проверка авторизации
    @Test
    fun auth_success() {
        val authHelper = MainActivity.AuthHelper()
        assertTrue(authHelper.isUserLoggedIn("validUser", "validPassword"))
    }
    @Test
    fun auth_fail() {
        val authHelper = MainActivity.AuthHelper()
        assertFalse(authHelper.isUserLoggedIn("invalidUser", "invalidPassword"))
    }
    @Test(expected = IllegalArgumentException::class)
    fun auth_emptyLogin() {
        val authHelper = MainActivity.AuthHelper()
        assertFalse(authHelper.isUserLoggedIn("", "validPassword"))
    }


    //  Валидация (MOCK)
    @Test
    fun validateInput() {
        val input = "test@example.com"
        assertTrue(isValidEmail(input))
    }

    private fun isValidEmail(email: String): Boolean {
        return email.contains("@")
    }
    @Test
    fun validateEmail_invalidEmail() {
        val validationHelper = MainActivity.ValidationHelper()
        assertFalse(validationHelper.isEmailValid("invalid_email"))
    }
    @Test
    fun validateEmail_emptyEmail() {
        val validationHelper = MainActivity.ValidationHelper()
        assertFalse(validationHelper.isEmailValid(""))
    }

   //Переход между активностями
   @Test
   fun testActivityTransition() {
       val mainActivity = MainActivity()
       val intent = mainActivity.startActivity(Intent(mainActivity, SecondActivity::class.java))
       assertNotNull(intent)
   }
}

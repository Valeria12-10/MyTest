package com.example.mytest

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.test.core.app.ActivityScenario
//import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            Toast.makeText(this, "Testing Activity Transition", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, SecondActivity::class.java))
            finish() // Завершаем MainActivity после запуска SecondActivity
        }
        // Пример перехода на другую активность
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }




    // Макет Api для тестирования
    class MockApi(val success: Boolean = true, val nullResponse: Boolean = false, val emptyResponse: Boolean = false ) {
        suspend fun fetchData(): String? {
            if (success) {
                return "Mocked Data"
            }else if(nullResponse){
                return null
            } else if(emptyResponse){
                return ""
            }
            else{
                throw Exception("Fetch failed")
            }
        }
    }


    // Макет помощника по аутентификации для тестирования
    class AuthHelper(){
        fun isUserLoggedIn(user:String, pass:String): Boolean{
            if(user.isEmpty()){
                throw IllegalArgumentException("User can not be empty")
            }
            if(pass.isEmpty()){
                throw IllegalArgumentException("Pass can not be empty")
            }

            if(user == "validUser" && pass == "validPassword"){
                return true;
            }
            return false;
        }
    }

    //Помощник по проверке макета
    class ValidationHelper(){
        fun isEmailValid(email:String): Boolean{
            if (email.isEmpty()){
                return false
            }

            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }
    @RunWith(AndroidJUnit4::class)
    class ActivityTransitionTest {

        private lateinit var scenario: ActivityScenario<MainActivity>

        @Before
        fun setup() {
            //scenario = launchActivity()
        }

        @After
        fun tearDown() {
            scenario.close()
        }
    }

}

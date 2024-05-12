package com.example.taskease_task_management_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GetstartedActivity : AppCompatActivity() {

    lateinit var getStarted: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getstarted)

        getStarted = findViewById(R.id.getstartedButton)

        val additionalButton = findViewById<Button>(R.id.getstartedButton)
        additionalButton.setOnClickListener {
            val intent = Intent(this,MainActivity ::class.java)
            startActivity(intent)

            Toast.makeText(this, "Redirecting to main dashbaord!", Toast.LENGTH_SHORT).show()
        }
    }
}
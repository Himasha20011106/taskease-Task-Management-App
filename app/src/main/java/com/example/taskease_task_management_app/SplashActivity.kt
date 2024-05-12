package com.example.taskease_task_management_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Delayed execution of navigation to another activity after 3 seconds
        Handler().postDelayed({
            // Start the new activity
            startActivity(Intent(this, GetstartedActivity::class.java))

            // Close this activity
            finish()
        }, 1000)
    }
}
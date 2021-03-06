package com.proyectomovil.tualcancia

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.proyectomovil.tualcancia.login.LoginActivity
import com.proyectomovil.tualcancia.login.goToActivity

//Login y Main Activity
class MainEmptyActivity  : AppCompatActivity() {

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(mAuth.currentUser == null){

            goToActivity<LoginActivity>{
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

        }else{
            goToActivity<MainActivity>{
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

        }
        finish()

    }
}

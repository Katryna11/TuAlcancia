package com.proyectomovil.tualcancia.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.proyectomovil.tualcancia.*
import kotlinx.android.synthetic.main.activity_forgot_password.*

class ForgotPasswordActivity : AppCompatActivity() {

    private val mAuth: FirebaseAuth by lazy {FirebaseAuth.getInstance()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        editTextEmail.validate{
            editTextEmail.error = if(isValidEmail(it)) null else "Por favor ingrese un Email valido"
        }
        buttonGoLogIn.setOnClickListener {
            goToActivity<LoginActivity>{
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        buttonForgot.setOnClickListener {
            val email = editTextEmail.text.toString()
            if(isValidEmail(email)){
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(this){
                    toast("El correo ha sido enviado para restablecer tu contraseña.")
                    goToActivity<LoginActivity>{
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                }
            }else{
                toast("Por favor asegurate de que la dirección de correo es correcta.")

            }
        }
    }
}

package com.proyectomovil.tualcancia.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.proyectomovil.tualcancia.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.editTextEmail
import kotlinx.android.synthetic.main.activity_login.editTextPassword

class LoginActivity : AppCompatActivity() {

    private val mAuth:FirebaseAuth by lazy {FirebaseAuth.getInstance()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


          buttonLogIn.setOnClickListener {
              val email = editTextEmail.text.toString()
              val password= editTextPassword.text.toString()

              if(isValidEmail(email) && isValidPassword(password)) {
                  logInByEmail(email,password)
              }else{
                      toast("Por favor confirma que todos los datos son correctos")
                  }


          }

        textViewForgotPassword.setOnClickListener{
            goToActivity<ForgotPasswordActivity>()

            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        }

        editTextEmail.validate{
            editTextEmail.error = if(isValidEmail(it)) null else "Por favor ingrese un Email valido"
        }
        editTextPassword.validate{
            editTextPassword.error = if(isValidPassword(it)) null else "La contraseña debería contener 8 digitos compuestos por una letra mayúscula, minúscula y un número."
        }
        buttonCreateAccount.setOnClickListener {
            goToActivity<SignUpActivity>()
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)

        }


    }

    private fun logInByEmail(email:String,password:String){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener( this){task ->
            if(task.isSuccessful) {
                //Verificación de si el usuario ha confirmado el email para poder hacer loguearse
                if (mAuth.currentUser!!.isEmailVerified) {
                    toast(" El usuario ha ingresado.")
                } else {
                    toast(" El usuario debe confirmar el email primero.")
                }

            }else{toast(" Un inesperado error ha ocurrido, por favor intente de nuevo")
            }
        }

    }

}

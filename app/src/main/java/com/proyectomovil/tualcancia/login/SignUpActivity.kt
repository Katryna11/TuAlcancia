package com.proyectomovil.tualcancia.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.proyectomovil.tualcancia.*
import kotlinx.android.synthetic.main.activity_sign_up.*



class SignUpActivity : AppCompatActivity() {
    private val mAuth: FirebaseAuth by lazy {FirebaseAuth.getInstance()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

       //Revisar
        buttonGoLogin.setOnClickListener {
            goToActivity<LoginActivity> {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)


        }
       buttonSignUp.setOnClickListener {

           val email=editTextEmail.text.toString()
           val password= editTextPassword.text.toString()
           val confirmPassword = editTextConfirmPassword.text.toString()

           if(isValidEmail(email) && isValidPassword(password) && isValidConfirmPassword(password,confirmPassword)) {
               signUpByEmail(email,password)
           }else{
               toast("Por favor confirma que todos los datos son correctos")
           }
       }

       editTextEmail.validate{
           editTextEmail.error = if(isValidEmail(it)) null else "Por favor ingrese un Email valido"
       }
        editTextPassword.validate{
            editTextPassword.error = if(isValidPassword(it)) null else "La contraseña debería contener 8 digitos compuestos por una letra mayúscula, minúscula y un número."
        }
        editTextConfirmPassword.validate{
            editTextConfirmPassword.error = if(isValidConfirmPassword(editTextPassword.text.toString(),it)) null else "Las contraseñas no coinciden, por favor intenta de nuevo."
        }

    }

    private fun signUpByEmail(email:String,password: String){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    toast("Un Email te ha sido enviado. Por favor, confirmar antes de ingresar.")
                    goToActivity<LoginActivity> {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

                } else {
                    toast("Un inesperado error ocurrió por favor intentalo de nuevo.")
                }
            }
    }



}

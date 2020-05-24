package com.proyectomovil.tualcancia.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.proyectomovil.tualcancia.R
import com.proyectomovil.tualcancia.goToActivity
import com.proyectomovil.tualcancia.toast
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

           if(isValidEmailAndPassword(email,password)){
               signUpByEmail(email,password)


           }else{
               toast("Por favor complete todos los datos y confirme si la contraseña es correcta.")

           }
           val intent = Intent(this,
               LoginActivity::class.java )
           //Este Intent nos permite salirnos de una vista por medio de el boton del celular retroceder.
           intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
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

    //Validación campos vacios
    private fun isValidEmailAndPassword(email: String, password: String):Boolean{
        return !email.isNullOrEmpty() &&
               !password.isNullOrEmpty() &&
                password ==  editTextConfirmPassword.text.toString()
    }

}

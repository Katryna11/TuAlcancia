package com.proyectomovil.tualcancia.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.proyectomovil.tualcancia.toast
import com.google.firebase.auth.FirebaseAuth
import com.proyectomovil.tualcancia.R
import com.proyectomovil.tualcancia.goToActivity
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.editTextEmail

class LoginActivity : AppCompatActivity() {

    private val mAuth:FirebaseAuth by lazy {FirebaseAuth.getInstance()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(mAuth.currentUser == null){
            toast("Nope")
        }else {
            toast("Yep")
            mAuth.signOut()
        }
          buttonLogIn.setOnClickListener {
              val email = editTextEmail.text.toString()
              val password= editTextPassword.text.toString()

              if(IsValidEmailAndPassword(email,password)){
                  LogInByEmail(email, password )

              }


          }
        textViewForgotPassword.setOnClickListener{goToActivity<ForgotPasswordActivity>()}
        buttonCreateAccount.setOnClickListener { goToActivity<SignUpActivity>() }


    }

    private fun LogInByEmail(email:String,password:String){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener( this){task ->
            if(task.isSuccessful){
            toast(" El usuario ha ingresado")
            }else{
                toast(" Un inesperado error ha ocurrido, por favor intente de nuevo")
            }
        }

    }

    private fun IsValidEmailAndPassword(email:String,password: String): Boolean{
        return !email.isNullOrEmpty() && !password.isNullOrEmpty()
    }

}

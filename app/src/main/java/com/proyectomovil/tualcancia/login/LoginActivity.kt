package com.proyectomovil.tualcancia.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.proyectomovil.tualcancia.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.editTextEmail
import kotlinx.android.synthetic.main.activity_login.editTextPassword

class LoginActivity : AppCompatActivity(),GoogleApiClient.OnConnectionFailedListener {

    private val mAuth:FirebaseAuth by lazy {FirebaseAuth.getInstance()}
    private val mGoogleApiClient: GoogleApiClient by lazy {getGoogleApiClient()}
    private val RC_GOOGLE_SIGN_IN = 99


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



          buttonLogIn.setOnClickListener {
              val email = editTextEmail.text.toString()
              val password= editTextPassword.text.toString()

              if(isValidEmail(email) && isValidPassword(password)) {
                  logInByEmail(email,password)
                  goToActivity<MainActivity>()

                  overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
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

        buttonLogInGoogle.setOnClickListener {
            val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
            startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN )

        }


    }

    private fun getGoogleApiClient():GoogleApiClient{
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleApiClient.Builder(this)
            .enableAutoManage(this,this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()
    }

    private fun loginByGoogleAccountIntoFirebase(googleAccount : GoogleSignInAccount){
        val credential = GoogleAuthProvider.getCredential(googleAccount.idToken, null)
        mAuth.signInWithCredential(credential).addOnCompleteListener(this){
            toast("Accedido por Google")
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

    override  fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_GOOGLE_SIGN_IN){
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if(result.isSuccess){
                val account = result.signInAccount
                loginByGoogleAccountIntoFirebase(account!!)
            }
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
    toast("Falló la conexión, por favor intente de nuevo.")
    }

}

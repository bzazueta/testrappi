package com.example.myrappi.ui.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.myrappi.R

class IntroActivity : AppCompatActivity() {
    internal var tiempoEspera = 2500
    private val permissions = arrayOf(

        Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)

    private val CAMERA_PERMISSION_REQUEST_CODE = 1
    private val SENSOR_PERMISSION_REQUEST_CODE = 2
    private val MULTIPLE_PERMISSIONS_REQUEST_CODE = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        if (ActivityCompat.checkSelfPermission(
                this@IntroActivity,
                permissions.get(0)
            ) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                this@IntroActivity,
                permissions.get(1)
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //Si alguno de los permisos no esta concedido lo solicita
            ActivityCompat.requestPermissions(
                this@IntroActivity,
                permissions,
                MULTIPLE_PERMISSIONS_REQUEST_CODE
            )
        } else {
            //Si todos los permisos estan concedidos prosigue con el flujo normal
           permissionGranted()
        }
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST_CODE ->                 //Se verifica si existen resultados y se valida si el permiso fue concedido o no
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Si el permiso está concedido prosigue con el flujo normal
                    permissionGranted()
                } else {
                    //Si el permiso no fue concedido no se puede continuar
                    permissionRejected()
                }
           SENSOR_PERMISSION_REQUEST_CODE ->                 //Se verifica si existen resultados y se valida si el permiso fue concedido o no
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Si el permiso está concedido prosigue con el flujo normal
                    permissionGranted()
                } else {
                    //Si el permiso no fue concedido no se puede continuar
                    permissionRejected()
                }
            MULTIPLE_PERMISSIONS_REQUEST_CODE ->                 //Verifica si todos los permisos se aceptaron o no
                if (validatePermissions(grantResults)) {
                    //Si todos los permisos fueron aceptados continua con el flujo normal
                    permissionGranted()
                } else {
                    //Si algun permiso fue rechazado no se puede continuar
                    permissionRejected()
                }
        }
    }

    private fun validatePermissions(grantResults: IntArray): Boolean {
        var allGranted = false
        //Revisa cada uno de los permisos y si estos fueron aceptados o no
        for (i in permissions.indices) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                //Si todos los permisos fueron aceptados retorna true
                allGranted = true
            } else {
                //Si algun permiso no fue aceptado retorna false
                allGranted = false
                break
            }

        }
        return allGranted
    }

    private fun permissionGranted() {

        intent()

    }

    private fun permissionRejected() {
        //Toast.makeText(Login.this,"permission_rejected", Toast.LENGTH_SHORT).show();
    }

    override fun onRestart() {
        intent()
        super.onRestart()
    }

    fun intent ()
    {
        Handler().postDelayed({
            //Pasar a la siguiente actividad
            val intent = Intent(this@IntroActivity,MainActivity::class.java)//Main2Activity
            startActivity(intent)
            //finish()
        }, tiempoEspera.toLong())
    }

}
package co.edu.upb.ejercicios.vistas_recicladas_sp08_02_2024

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import co.edu.upb.ejercicios.vistas_recicladas_sp08_02_2024.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() , OnClickListener{

    private lateinit var userAdapter: UserAdapter

    private lateinit var  linearLayoutManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {




        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // todo inicio shared preference

             val preferences = getPreferences(Context.MODE_PRIVATE) // Rinconcito en la memoria con la que va a trabajar

            val isFirstTime = preferences.getBoolean(getString(R.string.sp_first_time), true)

            if(isFirstTime){

                val dialogView = layoutInflater.inflate(R.layout.welcome_dialog, null)


                MaterialAlertDialogBuilder(this)
                    .setTitle("Bienvenido usuario nuevo")
                    .setView(dialogView)
                    .setCancelable(false)
                    .setPositiveButton("Registrar",{dialogInterface, i->

                        val userName = dialogView.findViewById<EditText>(R.id.etNameUser).text.toString()

                        with(preferences.edit()){
                            putBoolean(getString(R.string.sp_first_time), false)
                            putString("user_name", userName)
                                .apply()


                        }


                    })

                    .show()

            }




        // todo fin shared preferences


        userAdapter = UserAdapter(getUsers(),this)
        linearLayoutManager = LinearLayoutManager(this)

        findViewById<RecyclerView>(R.id.rvPrincipal).apply {

            layoutManager = linearLayoutManager
            adapter = userAdapter

        }



    }



    // Metodo para crear crear una lista de usuarios

        private fun getUsers(): MutableList<User>{

            val users = mutableListOf<User>()
            val queue =  Volley.newRequestQueue(this)  // Propiedades para hacer una solicitud por medio de un HTTP

            val url ="https://reqres.in/api/users"

            val jsonObjReq = JsonObjectRequest(

                Request.Method.GET, url, null ,{ res ->

                    val arrayApi = res.getJSONArray("data")  // Array temporal

                    for(i in 0..arrayApi.length()){
                        // el Array comienza en 0 hasta array.Length       // Configuracion
                        val jsonObject = arrayApi.getJSONObject(i)      // Transformar
                        val user = User(
                            jsonObj.getInt("id"),
                            jsonObj.getString("email"),
                            jsonObj.getString("first_name"),
                            jsonObj.getString("last_name"),
                            jsonObj.getString("avatar"),
                        )
                        users.add(user)

                    }

                },  { err ->

                    err.printStackTrace()

                }


            )

            queue.add(jsonObjReq)       // Objeto que va a configurar y recibir un

            return users

        }

    override fun onClick(user: User, position: Int) {

        Toast.makeText(this,"position:${user.getFullName()}",Toast.LENGTH_SHORT).show()

    }
}


// dependencies {
//    implementation 'com.android.volley:volley:1.2.1'

en Gradle app / a nivel de app
//}
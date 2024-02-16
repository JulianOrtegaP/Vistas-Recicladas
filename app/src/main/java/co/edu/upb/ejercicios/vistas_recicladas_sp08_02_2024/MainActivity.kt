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

            val user1 = User(1,"pikachu","giraldo","https://storage.needpix.com/rsynced_images/pokemon-1656997_1280.png")
            val user2 = User(2,"lionel","messi","https://vectorportal.com/storage/Messi-Barcelona_9077.jpg")
            val user3 = User(3,"Mother", "Flower", "https://comunicaciones.uis.edu.co/wp-content/uploads/2024/02/image-9-1024x576.png")

            users.add(user1)
            users.add(user2)
            users.add(user3)

            return users

        }

    override fun onClick(user: User, position: Int) {

        Toast.makeText(this,"position:${user.getFullName()}",Toast.LENGTH_SHORT).show()

    }
}
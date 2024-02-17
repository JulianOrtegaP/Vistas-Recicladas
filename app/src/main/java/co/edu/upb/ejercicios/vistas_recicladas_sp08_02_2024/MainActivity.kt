package co.edu.upb.ejercicios.vistas_recicladas_sp08_02_2024

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter: UserAdapter

    private lateinit var  linearLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {




        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        userAdapter = UserAdapter(getUsers())
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

}
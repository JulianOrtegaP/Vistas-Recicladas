package co.edu.upb.ejercicios.vistas_recicladas_sp08_02_2024

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.edu.upb.ejercicios.vistas_recicladas_sp08_02_2024.databinding.ItemUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

// RecyclerView.Adapter<> Esta

// Adapter vista que vamos a trabajar
// ViewHolder: Recibe y configura las propiedades de esa vista


class UserAdapter (val users: List<User> , private val listener: OnClickListener): RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    private lateinit var context:Context

    // onCreate: va a crear una vista






    // Inflar: Mostrar la vista

    // Tirar la vista para mostrar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)

        return  ViewHolder(view)
    }




 // Asignar la informacion
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user = users.get(position)

        with(holder){

            binding.tvOrden.text = (position+1).toString()

            binding.tvName.text= user.getFullName()

            Glide.with(context)
                .load(user.url)
                .centerCrop()
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)


                .into(binding.imgFoto)

        }



    }




    // getItemCount === va ser la variable "i"
    // Cuantas veces se va a repitir ese ciclo
    override fun getItemCount(): Int = users.size




    // Le estoy pasando las configuraciones de la vista
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val binding = ItemUserBinding.bind(view)  // Propiedades del item user se crea en binding

        fun setListener(user: User, position: Int){
            binding.root.setOnClickListener { listener.onClick(user,position) }

        }

    }


}
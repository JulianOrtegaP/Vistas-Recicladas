package co.edu.upb.ejercicios.vistas_recicladas_sp08_02_2024

data class User(

    var id:Int,
    var email: String,
    var first_name:String,
    var last_name:String,
    var  avatar: String){

    fun getFullName(): String ="$first_name, $last_name"


}

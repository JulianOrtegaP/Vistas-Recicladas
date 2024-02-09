package co.edu.upb.ejercicios.vistas_recicladas_sp08_02_2024

data class User(

    var id:Long,
    var name:String,
    var lastName:String,
    var  url: String){

    fun getFullName(): String ="$name $lastName"


}

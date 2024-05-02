package amaris.osorio.crud_amaris

import Modelo.ClaseConexion
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtNombre = findViewById<EditText>(R.id.txt_nombre)
        val txtCantidad = findViewById<EditText>(R.id.txt_cantidad)
        val txtPrecio =  findViewById<EditText>(R.id.txt_precio)
        val btn_agregar = findViewById<Button>(R.id.btn_agregar)

        btn_agregar.setOnClickListener{
            GlobalScope.launch(Dispatchers.IO){
                //Guardar datos
                //1. Crear un objeto de la clase conexión
                val ClaseConexion = ClaseConexion().cadenaConexion()
                //2. Creo una variable que contenga un PreparedStament
                val addProducto = ClaseConexion?.prepareStatement("insert into tbProductos(nombreProducto, precio, cantidad) values(?,?,?)")!!

                addProducto.setString(1, txtNombre.text.toString())
                addProducto.setInt(2, txtPrecio.text.toString().toInt())
                addProducto.setInt(3, txtCantidad.text.toString().toInt())
                addProducto.executeUpdate()
            }
        }
    }
}
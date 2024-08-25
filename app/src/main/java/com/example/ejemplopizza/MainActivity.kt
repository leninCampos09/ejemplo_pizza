package com.example.ejemplopizza

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        // Referencias a los componentes del layout
        val radioGroupPizzaSize: RadioGroup = findViewById(R.id.radioGroupPizzaSize)
        val checkCheese: CheckBox = findViewById(R.id.checkCheese)
        val checkBacon: CheckBox = findViewById(R.id.checkBacon)
        val checkMushrooms: CheckBox = findViewById(R.id.checkMushrooms)
        val buttonOrder: Button = findViewById(R.id.buttonOrder)
        val textViewOrderSummary: TextView = findViewById(R.id.textViewOrderSummary)

        // Configurar el botón de orden
        buttonOrder.setOnClickListener {
            // Obtener el tamaño de la pizza seleccionado
            val selectedSizeId = radioGroupPizzaSize.checkedRadioButtonId
            val pizzaSize = when (selectedSizeId) {
                R.id.radioSmall -> "Pequeña"
                R.id.radioMedium -> "Mediana"
                R.id.radioLarge -> "Grande"
                else -> ""
            }

            // Calcular el costo base de la pizza
            val basePrice = when (selectedSizeId) {
                R.id.radioSmall -> 8.00
                R.id.radioMedium -> 10.00
                R.id.radioLarge -> 12.00
                else -> 0.00
            }

            // Calcular los costos adicionales
            var extraCost = 0.00
            val extras = StringBuilder("Extras: ")

            if (checkCheese.isChecked) {
                extraCost += 2.00
                extras.append("Queso, ")
            }
            if (checkBacon.isChecked) {
                extraCost += 4.00
                extras.append("Tocino, ")
            }
            if (checkMushrooms.isChecked) {
                extraCost += 6.00
                extras.append("Champiñones, ")
            }

            // Quitar la última coma y espacio
            if (extras.length > 8) {
                extras.setLength(extras.length - 2)
            } else {
                extras.append("Ninguno")
            }

            // Calcular el precio total
            val totalPrice = basePrice + extraCost

            // Mostrar el resumen de la orden
            val orderSummary = "Tamaño: $pizzaSize\n$extras\nPrecio Total: $$totalPrice"
            textViewOrderSummary.text = orderSummary
    }
}
}
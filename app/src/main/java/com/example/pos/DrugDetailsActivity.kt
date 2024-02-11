import android.annotation.SuppressLint
import com.example.pos.R
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DrugDetailsActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.xml.activity_drug_details)

        // Get drug details from intent
        val intent = intent
        val drugName = intent.getStringExtra("drug_name")
        val drugPrice = intent.getDoubleExtra("drug_price", 0.0)
        val drugQuantity = intent.getIntExtra("drug_quantity", 0)

        // Set drug details to TextViews
        val nameTextView: TextView = findViewById(R.id.nameTextView)
        val priceTextView: TextView = findViewById(R.id.priceTextView)
        val quantityTextView: TextView = findViewById(R.id.quantityTextView)

        nameTextView.text = "Name: $drugName"
        priceTextView.text = "Price: $drugPrice"
        quantityTextView.text = "Quantity: $drugQuantity"
    }
}

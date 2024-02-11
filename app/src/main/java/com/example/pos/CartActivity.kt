import android.annotation.SuppressLint
import com.example.pos.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class CartActivity : AppCompatActivity() {

    private lateinit var cartListView: ListView

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.xml.activity_cart)

        cartListView = findViewById(R.id.cartListView)

        // Dummy data for the cart
        val cartItems = arrayOf("Item 1", "Item 2", "Item 3")

        // Create an ArrayAdapter to populate the ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, cartItems)
        cartListView.adapter = adapter
    }
}

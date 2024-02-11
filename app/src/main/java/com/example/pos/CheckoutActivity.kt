import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pos.R
import com.stripe.android.Stripe
import com.stripe.android.model.Token
import com.stripe.android.view.CardInputWidget
import com.stripe.android.model.Card
import com.example.pos.Drug


class CheckoutActivity : AppCompatActivity() {

    private lateinit var cartItems: ArrayList<Drug>
    private lateinit var cartListView: ListView
    private lateinit var totalPriceTextView: TextView
    private lateinit var payButton: Button
    private lateinit var cardInputWidget: CardInputWidget

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.xml.activity_checkout)

        // Initialize views
        cartListView = findViewById(R.id.cartListView)
        totalPriceTextView = findViewById(R.id.totalPriceTextView)
        payButton = findViewById(R.id.payButton)
        cardInputWidget = findViewById(R.id.cardInputWidget)

        // Retrieve cart items from intent
        cartItems = intent.getParcelableArrayListExtra("cartItems")
        // Retrieve cart items from intent
        cartItems = intent.getParcelableArrayListExtra<Drug>("cartItems") ?: ArrayList()


        // Display cart items in ListView
        val adapter = CartListAdapter(this, cartItems)
        cartListView.adapter = adapter

        // Calculate total price
        val totalPrice = calculateTotalPrice(cartItems)
        totalPriceTextView.text = "Total Price: $totalPrice"

        // Set up payment button click listener
        payButton.setOnClickListener {
            processPayment()
        }
    }

    private fun calculateTotalPrice(cartItems: ArrayList<Drug>): Double {
        var totalPrice = 0.0
        for (item in cartItems) {
            totalPrice += item.price * item.quantity
        }
        return totalPrice
    }

    private fun processPayment() {
        val card = cardInputWidget.card
        if (card != null) {
            val stripe = Stripe(applicationContext, "your_publishable_key")

            stripe.createToken(card, object : TokenCallback {
                override fun onSuccess(token: Token) {
                    // Send the token to your server to process the payment
                    val tokenId = token.id
                    // Handle successful payment
                    // You can navigate to a success screen or perform other actions
                }

                override fun onError(error: Exception) {
                    // Handle error
                }
            })
        }
    }
}

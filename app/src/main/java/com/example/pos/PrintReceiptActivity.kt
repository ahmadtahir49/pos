import android.annotation.SuppressLint
import com.example.pos.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class PrintReceiptActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.xml.activity_print_receipt)

        // Add your receipt printing logic here
    }
}

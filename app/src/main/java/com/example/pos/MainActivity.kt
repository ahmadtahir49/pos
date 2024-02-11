import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var drugListView: ListView
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drugListView = findViewById(R.id.drugListView)

        // Initialize the database
        try {
            db = this.openOrCreateDatabase("PharmacyDB", MODE_PRIVATE, null)
            createTableIfNotExists()
            populateDrugList()
        } catch (e: SQLiteException) {
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createTableIfNotExists() {
        db.execSQL("CREATE TABLE IF NOT EXISTS drugs (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, price REAL, quantity INTEGER)")
    }

    private fun populateDrugList() {
        val drugsList: ArrayList<String> = ArrayList()
        val cursor: Cursor = db.rawQuery("SELECT * FROM drugs", null)

        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val price = cursor.getDouble(cursor.getColumnIndex("price"))
                val quantity = cursor.getInt(cursor.getColumnIndex("quantity"))
                val drugInfo = "$name - $price - $quantity"
                drugsList.add(drugInfo)
            } while (cursor.moveToNext())
        }
        cursor.close()

        // Create an ArrayAdapter to populate the ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, drugsList)
        drugListView.adapter = adapter

        // Set a click listener for the items in the ListView
        drugListView.setOnItemClickListener { parent, view, position, id ->
            // Handle item click, you can navigate to drug details activity here
            Toast.makeText(this, "Clicked on drug: ${drugsList[position]}", Toast.LENGTH_SHORT).show()
        }
    }
}

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "PharmacyDB"
        private const val DATABASE_VERSION = 1

        // Table names
        private const val TABLE_DRUGS = "drugs"
        private const val TABLE_ORDERS = "orders"
        private const val TABLE_PAYMENTS = "payments"

        // Columns for the drugs table
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_PRICE = "price"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_STOCK = "stock"

        // Columns for the orders table
        private const val COLUMN_ORDER_ID = "order_id"
        private const val COLUMN_DRUG_ID = "drug_id"
        private const val COLUMN_QUANTITY = "quantity"
        private const val COLUMN_ORDER_DATE = "order_date"

        // Columns for the payments table
        private const val COLUMN_PAYMENT_ID = "payment_id"
        private const val COLUMN_ORDER_ID_FK = "order_id_fk"
        private const val COLUMN_AMOUNT = "amount"
        private const val COLUMN_PAYMENT_DATE = "payment_date"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create drugs table
        val createDrugsTableQuery = "CREATE TABLE $TABLE_DRUGS (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_PRICE REAL, " +
                "$COLUMN_DESCRIPTION TEXT, " +
                "$COLUMN_STOCK INTEGER)"
        db.execSQL(createDrugsTableQuery)

        // Create orders table
        val createOrdersTableQuery = "CREATE TABLE $TABLE_ORDERS (" +
                "$COLUMN_ORDER_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_DRUG_ID INTEGER, " +
                "$COLUMN_QUANTITY INTEGER, " +
                "$COLUMN_ORDER_DATE TEXT, " +
                "FOREIGN KEY($COLUMN_DRUG_ID) REFERENCES $TABLE_DRUGS($COLUMN_ID))"
        db.execSQL(createOrdersTableQuery)

        // Create payments table
        val createPaymentsTableQuery = "CREATE TABLE $TABLE_PAYMENTS (" +
                "$COLUMN_PAYMENT_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_ORDER_ID_FK INTEGER, " +
                "$COLUMN_AMOUNT REAL, " +
                "$COLUMN_PAYMENT_DATE TEXT, " +
                "FOREIGN KEY($COLUMN_ORDER_ID_FK) REFERENCES $TABLE_ORDERS($COLUMN_ORDER_ID))"
        db.execSQL(createPaymentsTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Handle database schema upgrades if needed
    }

    // Add methods for database operations (e.g., insert, query, update, delete) here
}

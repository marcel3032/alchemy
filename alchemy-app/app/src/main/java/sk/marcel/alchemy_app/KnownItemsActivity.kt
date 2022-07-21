package sk.marcel.alchemy_app

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class KnownItemsActivity : AppCompatActivity() {
    lateinit var jsonsHelpers: JsonsHelpers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_known_items)
        jsonsHelpers = JsonsHelpers(this)
        displayKnown()
    }

    private fun displayKnown(){
        val adapter = KnownItemAdapter(this, R.layout.item_layout, ArrayList(jsonsHelpers.getKnownItems()))
        findViewById<ListView>(R.id.known_items).adapter = adapter
    }
}
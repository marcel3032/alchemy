package sk.marcel.alchemy_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView

class RecipesActivity : AppCompatActivity() {
    lateinit var jsonsHelpers: JsonsHelpers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)
        jsonsHelpers = JsonsHelpers(this)

        val itemId = intent.getIntExtra(Constants.ITEM_ID_EXTRA, -1)
        findViewById<TextView>(R.id.title).text = "Známe recepty obsahujúce \"${Item.getItemById(itemId)?.itemName}\":"
        findViewById<ImageButton>(R.id.back_button).setOnClickListener { finish() }

        if(itemId!=-1) {
            val adapter = RecipesAdapter(this, R.layout.recipe_layout, jsonsHelpers.getKnownRecipes(itemId))
            findViewById<ListView>(R.id.known_recipes).adapter = adapter
        }
    }
}
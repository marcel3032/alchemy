package sk.marcel.alchemy_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView

class RecipesAdapter(private val recipesActivity: RecipesActivity, private val resourceLayout: Int, recipes: List<Recipe>) :
    ArrayAdapter<Recipe>(recipesActivity, resourceLayout, recipes) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v = convertView
        if (v == null) {
            v = LayoutInflater.from(recipesActivity).inflate(resourceLayout, parent, false)
        }

        val image1 = v!!.findViewById<ImageView>(R.id.item1)
        val image2 = v.findViewById<ImageView>(R.id.item2)
        val image3 = v.findViewById<ImageView>(R.id.item3)
        val result = v.findViewById<ImageView>(R.id.result)

        val recipe: Recipe? = getItem(position)

        if (recipe != null) {
            val items = ArrayList(recipe.items)
            items.sortBy { item -> item.tool }
            image1.setImageDrawable(recipesActivity.getDrawable(items[0].drawableId))
            image2.setImageDrawable(recipesActivity.getDrawable(items[1].drawableId))
            image3.setImageDrawable(recipesActivity.getDrawable(items[2].drawableId))
            result.setImageDrawable(recipesActivity.getDrawable(recipe.result.drawableId))
        }
        return v
    }
}
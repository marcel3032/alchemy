package sk.marcel.alchemy_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class RecipesAdapter(private val recipesActivity: RecipesActivity, private val resourceLayout: Int, recipes: List<Recipe>) :
    ArrayAdapter<Recipe>(recipesActivity, resourceLayout, recipes) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v = convertView
        if (v == null) {
            v = LayoutInflater.from(recipesActivity).inflate(resourceLayout, parent, false)
        }

        val image1 = v!!.findViewById<ImageView>(R.id.item1)
        val name1 = v.findViewById<TextView>(R.id.item1_name)
        val image2 = v.findViewById<ImageView>(R.id.item2)
        val name2 = v.findViewById<TextView>(R.id.item2_name)
        val image3 = v.findViewById<ImageView>(R.id.item3)
        val name3 = v.findViewById<TextView>(R.id.item3_name)
        val result = v.findViewById<ImageView>(R.id.result)
        val resultName = v.findViewById<TextView>(R.id.result_name)

        val recipe: Recipe? = getItem(position)

        if (recipe != null) {
            val items = ArrayList(recipe.items)
            items.sortBy { item -> item.tool }
            image1.setImageDrawable(recipesActivity.getDrawable(items[0].drawableId))
            name1.text = items[0].itemName
            name1.setTextColor(items[0].getColor())

            image2.setImageDrawable(recipesActivity.getDrawable(items[1].drawableId))
            name2.text = items[1].itemName
            name2.setTextColor(items[1].getColor())

            image3.setImageDrawable(recipesActivity.getDrawable(items[2].drawableId))
            name3.text = items[2].itemName
            name3.setTextColor(items[2].getColor())

            result.setImageDrawable(recipesActivity.getDrawable(recipe.result.drawableId))
            resultName.text = recipe.result.itemName
            resultName.setTextColor(recipe.result.getColor())
        }
        return v
    }
}
package sk.marcel.alchemy_app

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class KnownItemAdapter(private val knownItemsActivity: KnownItemsActivity, private val resourceLayout: Int, items: List<Item>) :
    ArrayAdapter<Item>(knownItemsActivity, resourceLayout, items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v = convertView
        if (v == null) {
            v = LayoutInflater.from(knownItemsActivity).inflate(resourceLayout, parent, false)
        }

        val name = v!!.findViewById<TextView>(R.id.item_name)
        val image = v.findViewById<ImageView>(R.id.item_image)
        val progressText = v.findViewById<TextView>(R.id.progress_text)
        val progressBar = v.findViewById<ProgressBar>(R.id.progress_bar)
        val item: Item? = getItem(position)
        if (item != null) {
            v.setOnClickListener {
                val intent = Intent(knownItemsActivity, RecipesActivity::class.java)
                intent.putExtra(Constants.ITEM_ID_EXTRA, item.itemId)
                knownItemsActivity.startActivity(intent)
            }

            name.text = item.itemName
            when (item.tool) {
                true -> name.setTextColor(Color.parseColor("#06ad00"))
                false -> name.setTextColor(Color.parseColor("#000a91"))
                else -> name.setTextColor(Color.parseColor("#8c0134"))
            }
            image.setImageDrawable(knownItemsActivity.getDrawable(item.drawableId))
            progressText.text = "${knownItemsActivity.jsonsHelpers.getKnownRecipesCount(item.itemId)}/${knownItemsActivity.jsonsHelpers.getAllRecipesCount(item)}"
            val allRecipesCount = knownItemsActivity.jsonsHelpers.getAllRecipesCount(item)
            if(allRecipesCount!=0)
                progressBar.progress = 100*knownItemsActivity.jsonsHelpers.getKnownRecipesCount(item.itemId)/allRecipesCount
            else
                progressBar.progress = 100
        }
        return v
    }
}
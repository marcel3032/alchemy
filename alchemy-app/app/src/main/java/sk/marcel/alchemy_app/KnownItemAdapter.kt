package sk.marcel.alchemy_app

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
            name.text = item.itemName
            image.setImageDrawable(knownItemsActivity.getDrawable(com.pnikosis.materialishprogress.R.drawable.abc_btn_radio_material))
            progressText.text = "${knownItemsActivity.jsonsHelpers.getKnownRecipesCount(item.itemId)}/${knownItemsActivity.jsonsHelpers.getAllRecipesCount(item)}"
            val allRecipesCount = knownItemsActivity.jsonsHelpers.getAllRecipesCount(item)
            if(allRecipesCount!=0)
                progressBar.progress = 100*knownItemsActivity.jsonsHelpers.getKnownRecipesCount(item.itemId)/allRecipesCount
            progressBar.progress = 100
        }
        return v
    }
}
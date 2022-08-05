package sk.marcel.alchemy_app

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class ChestItemAdapter(private val mainActivity: MainActivity, private val resourceLayout: Int, items: List<Item>) :
    ArrayAdapter<Item>(mainActivity, resourceLayout, items) {

    var itemChecked: ArrayList<Boolean> = ArrayList()

    init{
        for(i in items.indices)
            itemChecked.add(false)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v = convertView
        if (v == null) {
            v = LayoutInflater.from(mainActivity).inflate(resourceLayout, parent, false)
        }
        val marker = v!!.findViewById<CheckBox>(R.id.mark_for_delete)
        v.setOnClickListener {
            marker.isChecked = !marker.isChecked
            itemChecked[position] = marker.isChecked
            if(itemChecked.count{ it } > Constants.maxItemsOnCard){
                marker.isChecked = false
                itemChecked[position] = false
            }
        }
        marker.setOnCheckedChangeListener { _, b ->
            itemChecked[position] = b
            if(itemChecked.count{ it } > Constants.maxItemsOnCard){
                marker.isChecked = false
                itemChecked[position] = false
            }
        }
        val name = v.findViewById<TextView>(R.id.item_name)
        val image = v.findViewById<ImageView>(R.id.item_image)
        val item: Item? = getItem(position)
        if (item != null) {
            name.text = item.itemName
            if(item.tool)
                name.setTextColor(Color.parseColor("#06ad00"))
            else
                name.setTextColor(Color.parseColor("#000a91"))
            image.setImageDrawable(mainActivity.getDrawable(item.drawableId))
        }
        return v
    }
}
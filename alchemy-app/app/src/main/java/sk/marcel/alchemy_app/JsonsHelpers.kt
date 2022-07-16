package sk.marcel.alchemy_app

import android.util.Log
import org.json.JSONArray
import java.io.BufferedReader
import java.io.File
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList

class JsonsHelpers(activity: MainActivity) {
    private var chestFile: File = File(activity.filesDir.absolutePath, "chest.json")
    private var knownItemsFile: File = File(activity.filesDir.absolutePath, "known-items.json")

    init {
        if(!chestFile.exists()) {
            chestFile.createNewFile()
            chestFile.writeText("[]")
        }
        if(!knownItemsFile.exists()) {
            knownItemsFile.createNewFile()
            knownItemsFile.writeText("[]")
        }
    }

    private fun getItems(items: JSONArray): MutableList<Item>{
        val result = ArrayList<Item>()
        for(i in 0 until items.length()){
            val item = Constants.items[items.getInt(i)]
            if(item!=null)
                result.add(item)
            else
                Log.e("alchemy-app", "WTF, take id nie je")
        }
        return result
    }

    fun getChestItems(): MutableList<Item> {
        return getItems(getChestJson())
    }

    fun getKnownItems(): MutableSet<Item> {
        return HashSet<Item>(getItems(getKnownJson()))
    }

    private fun getChestJson(): JSONArray {
        BufferedReader(chestFile.reader()).use { reader ->
            return JSONArray(reader.readText())
        }
    }

    private fun getKnownJson(): JSONArray {
        BufferedReader(knownItemsFile.reader()).use { reader ->
            return JSONArray(reader.readText())
        }
    }

    fun writeChestItems(items: List<Item>){
        chestFile.writeText(getItemsString(items))
    }

    fun writeKnownItems(items: Set<Item>){
        knownItemsFile.writeText(getItemsString(ArrayList<Item>(items)))
    }

    private fun getItemsString(items: List<Item>):String{
        val jsonToWrite = JSONArray()
        for(item in items){
            jsonToWrite.put(item.itemId)
        }
        return jsonToWrite.toString()
    }
}
package sk.marcel.alchemy_app

import android.app.Activity
import android.content.Context
import android.util.Log
import org.json.JSONArray
import java.io.BufferedReader
import java.io.File
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class JsonsHelpers(activity: Context) {
    private var chestFile: File = File(activity.filesDir.absolutePath, "chest.json")
    private var knownItemsFile: File = File(activity.filesDir.absolutePath, "known-items.json")
    private var knownRecipesFile: File = File(activity.filesDir.absolutePath, "known-recipes.json")

    init {
        for(file in listOf(chestFile, knownItemsFile, knownRecipesFile)) {
            if (!file.exists()) {
                file.createNewFile()
                file.writeText("[]")
            }
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

    fun getKnownRecipes(): MutableSet<Int> {
        val recipes = getRecipesJson()
        val result = HashSet<Int>()
        for(i in 0 until recipes.length())
            result.add(recipes.getInt(i))
        return result
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

    private fun getRecipesJson(): JSONArray {
        BufferedReader(knownRecipesFile.reader()).use { reader ->
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

    fun getAllRecipesCount(item: Item): Int {
        return Constants.recipes.values.count { recipe -> recipe.items.contains(item) }
    }

    fun getKnownRecipesCount(itemId: Int): Int {
        return 2 //TODO get known recipes from known recipes book
    }
}
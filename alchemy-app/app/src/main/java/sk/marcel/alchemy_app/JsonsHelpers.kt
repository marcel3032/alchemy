package sk.marcel.alchemy_app

import android.content.Context
import android.util.Log
import org.json.JSONArray
import java.io.BufferedReader
import java.io.File

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

    fun resetFiles(){
        for(file in listOf(chestFile, knownItemsFile, knownRecipesFile)) {
            file.createNewFile()
            file.writeText("[]")
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
        result.sort()
        return result
    }

    fun getChestItems(): MutableList<Item> {
        return getItems(getChestJson())
    }

    fun getKnownItems(): MutableList<Item> {
        return getItems(getKnownJson())
    }

    fun getKnownRecipes(): MutableList<Int> {
        val recipes = getRecipesJson()
        val result = ArrayList<Int>()
        for(i in 0 until recipes.length())
            result.add(recipes.getInt(i))
        result.sort()
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
        chestFile.writeText(getItemsString(items.sorted()))
    }

    fun writeKnownItems(items: List<Item>){
        knownItemsFile.writeText(getItemsString(items.sorted()))
    }

    private fun getItemsString(items: List<Item>):String{
        val jsonToWrite = JSONArray()
        for(item in items.sorted()){
            jsonToWrite.put(item.itemId)
        }
        return jsonToWrite.toString()
    }

    fun writeKnownRecipes(recipes: MutableList<Int>){
        val jsonToWrite = JSONArray()
        for(recipe in recipes.sorted()){
            jsonToWrite.put(recipe)
        }
        knownRecipesFile.writeText(jsonToWrite.toString())
    }

    fun getAllRecipesCount(item: Item): Int {
        return Constants.recipes.values.count { recipe -> recipe.items.contains(item) || recipe.result==item }
    }

    fun getKnownRecipesCount(itemId: Int): Int {
        return Recipe.getRecipes(ArrayList(getKnownRecipes())).count { recipe -> recipe.items.map { item -> item.itemId }.contains(itemId) || recipe.result.itemId==itemId }
    }

    fun getKnownRecipes(itemId: Int): List<Recipe> {
        return Recipe.getRecipes(ArrayList(getKnownRecipes())).filter { recipe -> recipe.items.map { item -> item.itemId }.contains(itemId) || recipe.result.itemId==itemId }
    }
}
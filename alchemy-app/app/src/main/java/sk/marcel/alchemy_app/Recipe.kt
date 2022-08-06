package sk.marcel.alchemy_app

data class Recipe (val recipeId:Int, val items:List<Item>, val result: Item):Comparable<Recipe>{
    companion object {
        fun getRecipeById(id:Int): Recipe? {
            return Constants.recipes[id]
        }
        fun getRecipes(ids:List<Int>): List<Recipe>{
            val result = ArrayList<Recipe>()
            for(id in ids){
                getRecipeById(id)?.let { result.add(it) }
            }
            return result
        }
    }

    override fun compareTo(other: Recipe): Int {
        return result.compareTo(other.result)
    }
}
package sk.marcel.alchemy_app

data class Recipe (val recipeId:Int, val items:Set<Item>, val result: Item){
    companion object {
        fun getRecipeById(id:Int): Recipe? {
            return Constants.recipes[id]
        }
    }
}
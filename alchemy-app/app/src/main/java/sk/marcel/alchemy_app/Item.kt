package sk.marcel.alchemy_app

data class Item (val itemId:Int, val itemName:String, val tool:Boolean){
    companion object {
        fun getItemById(id:Int): Item? {
            return Constants.items[id]
        }
        fun getItems(ids:List<Int>): List<Item?>{
            val result = ArrayList<Item?>()
            for(id in ids){
                result.add(getItemById(id))
            }
            return result
        }
    }

}
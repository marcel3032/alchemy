package sk.marcel.alchemy_app

object Constants{
    private val indium = Item(1, "indium", false)
    private val kabel = Item(2, "kábel", false)
    private val kov = Item(3, "kov", false)
    private val piliny = Item(4, "kovové piliny", false)
    private val kremik = Item(5, "kremík", false)
    private val lyzica = Item(6, "lyžica", false)
    private val mapaAzie = Item(7, "mapa Ázie", false)
    private val med = Item(8, "meď", false)
    private val okuliare = Item(9, "okuliare", false)
    private val papier = Item(10, "papier", false)
    private val para = Item(11, "para", false)
    private val filter = Item(12, "pieskový filter", false)
    private val piesok = Item(13, "piesok", false)
    private val plast = Item(14, "plast", false)
    private val plosak= Item(15, "plošák", false)
    private val sklo = Item(16, "sklo", false)
    private val tuzemak = Item(17, "tuzemský um", false)
    private val vifonka = Item(18, "vifonka", false)
    private val vlasy = Item(19, "vlasy", false)
    private val voda = Item(20, "voda", false)
    private val kefka = Item(21, "zubná kefka", false)
    private val lepidlo = Item(32, "lepidlo", true)
    private val mikroskop = Item(33, "mikroskop", true)
    private val noznice = Item(34, "nožnice", true)
    private val pajkovacka = Item(35, "pájkovačka", true)
    private val pilnik = Item(36, "pilník", true)
    private val sroubovak = Item(37, "šroubovák", true)
    private val stetec = Item(38, "štetec", true)
    private val teplo = Item(39, "teplo", true)
    private val topanka = Item(40, "topánka", true)

    val items = mapOf(
        Pair(1, indium),
        Pair(2, kabel),
        Pair(3, kov),
        Pair(4, piliny),
        Pair(5, kremik),
        Pair(6, lyzica),
        Pair(7, mapaAzie),
        Pair(8, med),
        Pair(9, okuliare),
        Pair(10, papier),
        Pair(11, para),
        Pair(12, filter),
        Pair(13, piesok),
        Pair(14, plast),
        Pair(15, plosak),
        Pair(16, sklo),
        Pair(17, tuzemak),
        Pair(18, vifonka),
        Pair(19, vlasy),
        Pair(20, voda),
        Pair(21, kefka),
        Pair(32, lepidlo),
        Pair(33, mikroskop),
        Pair(34, noznice),
        Pair(35, pajkovacka),
        Pair(36, pilnik),
        Pair(37, sroubovak),
        Pair(38, stetec),
        Pair(39, teplo),
        Pair(40, topanka),
    )

    val recipes = mapOf(
        Pair(1, Recipe(1, setOf(pajkovacka, kabel, kov), piliny))
    )

    const val MAX_ITEMS_IN_CHEST = 5
    const val maxItemsOnCard = 3

}

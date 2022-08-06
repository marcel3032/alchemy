package sk.marcel.alchemy_app

object Constants{
    const val ITEM_ID_EXTRA = "item_id";

    private val indium = Item(1, "indium", false, R.drawable.ic_indium)
    private val kabel = Item(2, "kábel", false, R.drawable.ic_kabel)
    private val kov = Item(3, "kov", false, R.drawable.ic_kov)
    private val piliny = Item(4, "kovové piliny", false, R.drawable.ic_piliny)
    private val kremik = Item(5, "kremík", false, R.drawable.ic_kremik)
    private val lyzica = Item(6, "lyžica", false, R.drawable.ic_lyzica)
    private val mapaAzie = Item(7, "mapa Ázie", false, R.drawable.ic_mapaazie)
    private val med = Item(8, "meď", false, R.drawable.ic_med)
    private val okuliare = Item(9, "okuliare", false, R.drawable.ic_okuliare)
    private val papier = Item(10, "papier", false, R.drawable.ic_papier)
    private val para = Item(11, "para", false, R.drawable.ic_para)
    private val filter = Item(12, "pieskový filter", false, R.drawable.ic_filter)
    private val piesok = Item(13, "piesok", false, R.drawable.ic_piesok)
    private val plast = Item(14, "plast", false, R.drawable.ic_plast)
    private val plosak= Item(15, "plošák", false, R.drawable.ic_plosak)
    private val sklo = Item(16, "sklo", false, R.drawable.ic_sklo)
    private val tuzemak = Item(17, "tuzemský um", false, R.drawable.ic_tuzemak)
    private val vifonka = Item(18, "vifonka", false, R.drawable.ic_vifonka)
    private val vlasy = Item(19, "vlasy", false, R.drawable.ic_vlasy)
    private val voda = Item(20, "voda", false, R.drawable.ic_voda)
    private val kefka = Item(21, "zubná kefka", false, R.drawable.ic_kefka)
    private val lepidlo = Item(32, "lepidlo", true, R.drawable.ic_lepidlo)
    private val mikroskop = Item(33, "mikroskop", true, R.drawable.ic_mikroskop)
    private val noznice = Item(34, "nožnice", true, R.drawable.ic_noznice)
    private val pajkovacka = Item(35, "pájkovačka", true, R.drawable.ic_pajkovacka)
    private val pilnik = Item(36, "pilník", true, R.drawable.ic_pilnik)
    private val sroubovak = Item(37, "šroubovák", true, R.drawable.ic_sroubovak)
    private val stetec = Item(38, "štetec", true, R.drawable.ic_stetec)
    private val teplo = Item(39, "teplo", true, R.drawable.ic_teplo)
    private val topanka = Item(40, "topánka", true, R.drawable.ic_topanka)

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

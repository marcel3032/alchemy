package sk.marcel.alchemy_app

object Constants{
    const val ITEM_ID_EXTRA = "item_id"

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
    private val akvarium = Item(22, "akvárium", false, R.drawable.ic_akvarium)

    private val laser = Item(23, "laser", null, R.drawable.ic_laser)
    private val dym = Item(24, "dym", null, R.drawable.ic_dym)
    private val zrkadlo = Item(25, "zrkadlo", null, R.drawable.ic_mirror)

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
        Pair(22, akvarium),

        Pair(23, laser),
        Pair(24, dym),
        Pair(25, zrkadlo),

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
        Pair(1, Recipe(1, listOf(vifonka, sklo, topanka), tuzemak)),
        Pair(2, Recipe(2, listOf(filter, filter, topanka), piesok)),
        Pair(3, Recipe(3, listOf(voda, papier, topanka), vifonka)),
        Pair(4, Recipe(4, listOf(med, kremik, mikroskop), plosak)),
        Pair(5, Recipe(5, listOf(plosak, indium, mikroskop), laser)),
        Pair(6, Recipe(6, listOf(mapaAzie, tuzemak, noznice), indium)),
        Pair(7, Recipe(7, listOf(papier, vifonka, noznice), mapaAzie)),
        Pair(8, Recipe(8, listOf(kefka, kefka, noznice), vlasy)),
        Pair(9, Recipe(9, listOf(papier, vifonka, stetec), mapaAzie)),
        Pair(10, Recipe(10, listOf(sklo, kov, stetec), zrkadlo)),
        Pair(11, Recipe(11, listOf(plast, med, pajkovacka), plosak)),
        Pair(12, Recipe(12, listOf(sklo, kov, pilnik), okuliare)),
        Pair(13, Recipe(13, listOf(kov, kov, pilnik), piliny)),
        Pair(14, Recipe(14, listOf(okuliare, kov, sroubovak), mikroskop)),
        Pair(15, Recipe(15, listOf(lyzica, lyzica, sroubovak), noznice)),
        Pair(16, Recipe(16, listOf(plast, lyzica, lepidlo), sroubovak)),
        Pair(17, Recipe(17, listOf(lyzica, kabel, lepidlo), pajkovacka)),
        Pair(18, Recipe(18, listOf(lyzica, vlasy, lepidlo), stetec)),
        Pair(19, Recipe(19, listOf(sklo, sklo, lepidlo), akvarium)),
        Pair(20, Recipe(20, listOf(piesok, kov, lepidlo), pilnik)),
        Pair(21, Recipe(21, listOf(piesok, papier, lepidlo), pilnik)),
        Pair(22, Recipe(22, listOf(kefka, kefka, teplo), plast)),
        Pair(23, Recipe(23, listOf(filter, filter, teplo), med)),
        Pair(24, Recipe(24, listOf(sklo, sklo, teplo), kremik)),
        Pair(25, Recipe(25, listOf(plast, med, teplo), kabel)),
        Pair(26, Recipe(26, listOf(plast, lyzica, teplo), sroubovak)),
        Pair(27, Recipe(27, listOf(lyzica, lyzica, teplo), kov)),
        Pair(28, Recipe(28, listOf(piesok, piesok, teplo), sklo)),
        Pair(29, Recipe(29, listOf(voda, voda, teplo), para)),
        Pair(30, Recipe(30, listOf(lyzica, vifonka, teplo), para)),
        Pair(31, Recipe(31, listOf(voda, vifonka, teplo), lepidlo)),
        Pair(32, Recipe(32, listOf(piliny, para, teplo), dym)),
    )

    const val MAX_ITEMS_IN_CHEST = 5
    const val maxItemsOnCard = 3

}

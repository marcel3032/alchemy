package sk.marcel.alchemy_app

import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.allViews
import cn.pedant.SweetAlert.SweetAlertDialog

class MainActivity : AppCompatActivity() {
    private var mNfcAdapter: NfcAdapter? = null
    lateinit var jsonsHelpers: JsonsHelpers
    private val checkBoxIdToItemId = HashMap<Int, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
        jsonsHelpers = JsonsHelpers(this)
        displayChest()
        findViewById<Button>(R.id.delete_selected).setOnClickListener { deleteSelectedItems() }
        findViewById<Button>(R.id.known_items).setOnClickListener { startActivity(Intent(this, KnownItemsActivity::class.java)) }
        findViewById<Button>(R.id.reset_files).setOnClickListener { resetProgress() }
    }

    override fun onResume() {
        super.onResume()
        mNfcAdapter?.let {
            NFC.enableNFCInForeground(it, this,javaClass)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        if(intent!=null) {
            if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action || NfcAdapter.ACTION_TAG_DISCOVERED == intent.action || NfcAdapter.ACTION_TECH_DISCOVERED == intent.action) {

                val res: Pair<List<Item?>, List<Recipe>>? = if(findViewById<CheckBox>(R.id.pridaj_na_kartu).isChecked) {
                    val first = Integer.parseInt(findViewById<EditText>(R.id.first).text.toString())
                    val second = Integer.parseInt(findViewById<EditText>(R.id.second).text.toString())
                    val third = Integer.parseInt(findViewById<EditText>(R.id.third).text.toString())
                    NFC.readAndWriteItems(intent, listOf(Constants.items[first]!!, Constants.items[second]!!, Constants.items[third]!!))
                } else if(findViewById<CheckBox>(R.id.prenes_z_karty).isChecked) {
                    val toWrite = ArrayList<Item>()
                    val chestItems = jsonsHelpers.getChestItems()
                    for(view in findViewById<LinearLayout>(R.id.stored_items).allViews) {
                        if (view is CheckBox && view.isChecked) {
                            toWrite.add(Constants.items[checkBoxIdToItemId[view.id]]!!)
                            chestItems.remove(Constants.items[checkBoxIdToItemId[view.id]]!!)
                        }
                    }
                    jsonsHelpers.writeChestItems(chestItems)
                    NFC.readAndWriteItems(intent, toWrite)
                } else {
                    NFC.readItems(intent)
                }

                if(res!=null) {
                    val knownRecipes = jsonsHelpers.getKnownRecipes()
                    for(recipe in res.second)
                        knownRecipes.add(recipe.recipeId)
                    jsonsHelpers.writeKnownRecipes(knownRecipes)
                    val chestItems = jsonsHelpers.getChestItems()
                    val knownItems = jsonsHelpers.getKnownItems()
                    val stringBuilder = StringBuilder()
                    for(item in res.first) {
                        if (item != null) {
                            stringBuilder.append(item.itemName).append(", ")
                            if(findViewById<CheckBox>(R.id.prenes_z_karty).isChecked){
                                chestItems.add(item)
                                knownItems.add(item)
                            }
                        }
                        else
                            stringBuilder.append("nic, ")
                    }

                    jsonsHelpers.writeChestItems(chestItems)
                    jsonsHelpers.writeKnownItems(knownItems)

                    val alertDialog = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Načítaná karta")
                        .setContentText("Na karte: $stringBuilder")
                    alertDialog?.show()
                    displayChest()

                    playSound(R.raw.ack)
                } else {
                    val alertDialog = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Chyba pri čítaní karty")
                    alertDialog?.show()
                    playSound(R.raw.error)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mNfcAdapter?.let {
            NFC.disableNFCInForeground(it,this)
        }
    }

    private fun playSound(sound:Int){
        val mp: MediaPlayer = MediaPlayer.create(this, sound)
        mp.start()
        mp.setOnCompletionListener { mp.release() }
    }

    private fun displayChest(){
        val adapter = ChestItemAdapter(this, R.layout.chest_item_layout, jsonsHelpers.getChestItems())
        findViewById<ListView>(R.id.stored_items).adapter = adapter
    }

    private fun deleteSelectedItems(){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Delete selected items?")
            .setPositiveButton("Yes") { _: DialogInterface?, _: Int ->
                val chestItems = jsonsHelpers.getChestItems()
                val chestItemAdapter = findViewById<ListView>(R.id.stored_items).adapter as ChestItemAdapter

                for(i in chestItemAdapter.itemChecked.indices.reversed())
                    if(chestItemAdapter.itemChecked[i])
                        chestItems.removeAt(i)

                jsonsHelpers.writeChestItems(chestItems)
                displayChest()
            }
            .setNegativeButton("Cancel") { dialog: DialogInterface, _: Int -> dialog.cancel() }
        val alert = builder.create()
        alert.show()
    }

    private fun resetProgress(){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Reset game progress?")
            .setPositiveButton("Yes") { _: DialogInterface?, _: Int ->
                jsonsHelpers.resetFiles()
                displayChest()
            }
            .setNegativeButton("Cancel") { dialog: DialogInterface, _: Int -> dialog.cancel() }
        val alert = builder.create()
        alert.show()
    }
}
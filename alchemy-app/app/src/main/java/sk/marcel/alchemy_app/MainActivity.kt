package sk.marcel.alchemy_app

import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import android.nfc.NfcAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog

class MainActivity : AppCompatActivity() {
    private var mNfcAdapter: NfcAdapter? = null
    lateinit var jsonsHelpers: JsonsHelpers

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

                val res: Pair<List<Item?>?, List<Recipe>>?
                if(findViewById<CheckBox>(R.id.rw_z_karty).isChecked) {
                    val toWrite = ArrayList<Item>()
                    val chestItems = jsonsHelpers.getChestItems()
                    val chestItemAdapter = findViewById<ListView>(R.id.stored_items).adapter as ChestItemAdapter

                    for(i in chestItemAdapter.itemChecked.indices.reversed()) {
                        if (chestItemAdapter.itemChecked[i]) {
                            toWrite.add(chestItems[i])
                            chestItems.remove(chestItems[i])
                        }
                    }
                    res = NFC.readAndWriteItems(intent, toWrite, Constants.MAX_ITEMS_IN_CHEST - chestItems.size)
                    if(res?.first !=null)
                        jsonsHelpers.writeChestItems(chestItems)
                } else {
                    res = NFC.readItems(intent, Int.MAX_VALUE)
                }

                if(res!=null) {
                    val knownRecipes = jsonsHelpers.getKnownRecipes()
                    for(recipe in res.second)
                        knownRecipes.add(recipe.recipeId)
                    jsonsHelpers.writeKnownRecipes(knownRecipes)
                    val chestItems = jsonsHelpers.getChestItems()
                    val knownItems = jsonsHelpers.getKnownItems()
                    val stringBuilder = StringBuilder()
                    if(res.first!=null) {
                        for (item in res.first!!) {
                            if (item != null) {
                                stringBuilder.append(item.itemName).append(", ")
                                if (findViewById<CheckBox>(R.id.rw_z_karty).isChecked)
                                    chestItems.add(item)
                                knownItems.add(item)
                            } else
                                stringBuilder.append("nic, ")
                        }
                        jsonsHelpers.writeChestItems(chestItems)
                        jsonsHelpers.writeKnownItems(knownItems)

                        val str = if(findViewById<CheckBox>(R.id.rw_z_karty).isChecked) "Zobraté:"
                                  else "Na karte:"
                        SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText(str)
                            .setContentText("$stringBuilder")
                            .show()

                        playSound(R.raw.ack)
                        displayChest()
                    } else {
                        SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Načítaná karta")
                            .setContentText("Prenosom by bola prekročená kapacita")
                            .show()

                        playSound(R.raw.error)
                        displayChest()
                    }

                } else {
                    SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Chyba pri čítaní karty")
                        .show()

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
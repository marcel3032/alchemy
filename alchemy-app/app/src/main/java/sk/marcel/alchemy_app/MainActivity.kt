package sk.marcel.alchemy_app

import android.content.Intent
import android.media.MediaPlayer
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.allViews

class MainActivity : AppCompatActivity() {
    private var mNfcAdapter: NfcAdapter? = null
    private lateinit var jsonsHelpers: JsonsHelpers
    private val checkBoxIdToItemId = HashMap<Int, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
        jsonsHelpers = JsonsHelpers(this)
        displayChest()
        displayKnown()
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

                val res: List<Item?>? = if(findViewById<CheckBox>(R.id.pridaj_na_kartu).isChecked) {
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
                    Toast.makeText(this, "OK", Toast.LENGTH_LONG).show()
                    val chestItems = jsonsHelpers.getChestItems()
                    val knownItems = jsonsHelpers.getKnownItems()
                    val stringBuilder = StringBuilder()
                    for(item in res) {
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

                    findViewById<TextView>(R.id.result).text = stringBuilder
                    displayChest()
                    displayKnown()

                    playSound(R.raw.ack)
                } else {
                    Toast.makeText(this, "reading failed", Toast.LENGTH_LONG).show()
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
        findViewById<LinearLayout>(R.id.stored_items).removeAllViews()
        val chestItems = jsonsHelpers.getChestItems()
        for(item in chestItems){
            val text = CheckBox(this)
            text.id = View.generateViewId()
            checkBoxIdToItemId[text.id] = item.itemId
            text.text = item.itemName
            findViewById<LinearLayout>(R.id.stored_items).addView(text)
        }
    }

    private fun displayKnown(){
        findViewById<LinearLayout>(R.id.known_items).removeAllViews()
        val knownItems = jsonsHelpers.getKnownItems()
        for(item in knownItems){
            val text = TextView(this)
            text.text = item.itemName
            findViewById<LinearLayout>(R.id.known_items).addView(text)
        }

    }
}
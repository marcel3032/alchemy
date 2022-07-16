package sk.marcel.alchemy_app

import android.content.Intent
import android.media.MediaPlayer
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var mNfcAdapter: NfcAdapter? = null
    private lateinit var jsonsHelpers: JsonsHelpers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
        jsonsHelpers = JsonsHelpers(this)
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
                val first = Integer.parseInt(findViewById<EditText>(R.id.first).text.toString())
                val second = Integer.parseInt(findViewById<EditText>(R.id.second).text.toString())
                val third = Integer.parseInt(findViewById<EditText>(R.id.third).text.toString())
                val res = NFC.readAndWriteItems(intent, listOf(Constants.items[first]!!, Constants.items[second]!!, Constants.items[third]!!))
                if(res!=null) {
                    Toast.makeText(this, "OK", Toast.LENGTH_LONG).show()
                    val stringBuilder = StringBuilder()
                    for(item in res)
                        if (item != null)
                            stringBuilder.append(item.itemName).append(", ")

                    findViewById<TextView>(R.id.result).text = stringBuilder

                    val mp: MediaPlayer = MediaPlayer.create(this, R.raw.ack)
                    mp.start()
                    mp.setOnCompletionListener { mp.release() }
                } else {
                    Toast.makeText(this, "reading failed", Toast.LENGTH_LONG).show()

                    val mp: MediaPlayer = MediaPlayer.create(this, R.raw.error)
                    mp.start()
                    mp.setOnCompletionListener { mp.release() }
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
}
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

class KnownItemsActivity : AppCompatActivity() {
    lateinit var jsonsHelpers: JsonsHelpers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_known_items)
        jsonsHelpers = JsonsHelpers(this)
        displayKnown()
    }

    private fun displayKnown(){
        val adapter = KnownItemAdapter(this, R.layout.item_layout, jsonsHelpers.getChestItems())
        findViewById<ListView>(R.id.known_items).adapter = adapter
    }
}
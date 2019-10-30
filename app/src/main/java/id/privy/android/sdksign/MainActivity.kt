package id.privy.android.sdksign

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import id.privy.android.sign.PrivySDK
import id.privy.android.sign.PrivySignUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val documentToken = token_document.text.toString()
        open_tab.setOnClickListener {
            PrivySDK(context = this)
                .openDocument(
                    tokenDocument = documentToken,
                    queryDocument = PrivySignUtils.paramsRequest(
                        privyID = "RA3150",
                        visibility = true,
                        coordinateX = 300f,
                        coordinateY = 200f
                    )
                )
        }
        open_view.setOnClickListener {
            val intent = Intent(this, ViewSampleActivity::class.java)
            intent.putExtra("params", documentToken)
            startActivity(intent)
        }
    }
}

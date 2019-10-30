package id.privy.android.sdksign

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.ValueCallback
import android.widget.Toast
import id.privy.android.sign.PrivySignUtils
import kotlinx.android.synthetic.main.activity_view_sample.*

class ViewSampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_sample)

        val documentToken = intent.getStringExtra("params")
        viewer.setCallbackSign(object : PrivySignUtils.PrivySignCallback{
            override fun afterAction() {
                // what do you want after action?
                Toast.makeText(this@ViewSampleActivity, "After Action", Toast.LENGTH_SHORT).show()
            }

            override fun afterSign() {
                // what do you want after sign?
                Toast.makeText(this@ViewSampleActivity, "After Sign", Toast.LENGTH_SHORT).show()
            }

            override fun afterReview() {
                // what do you want after review?
                Toast.makeText(this@ViewSampleActivity, "After Review", Toast.LENGTH_SHORT).show()
            }

        })

        viewer.openDocument(tokenDocument = documentToken,
            queryDocument = PrivySignUtils.paramsRequest(
            privyID = "RA3150",
            visibility = true,
            coordinateX = 300f,
            coordinateY = 200f
        ))
    }
}

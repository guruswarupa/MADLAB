package com.guruswarupa.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class MainActivity : AppCompatActivity() {

    private lateinit var btnParse: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnParse = findViewById(R.id.btnParse)
        tvResult = findViewById(R.id.tvResult)

        btnParse.setOnClickListener {
            parseXML()
        }
    }

    private fun parseXML() {
        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = resources.getXml(R.xml.data)
            var eventType = parser.eventType
            val result = StringBuilder()
            var tagName = ""

            while (eventType != XmlPullParser.END_DOCUMENT) {
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        tagName = parser.name
                    }
                    XmlPullParser.TEXT -> {
                        val text = parser.text.trim()
                        if (text.isNotEmpty()) {
                            result.append("$tagName: $text\n")
                        }
                    }
                }
                eventType = parser.next()
            }

            tvResult.text = result.toString()

        } catch (e: Exception) {
            e.printStackTrace()
            tvResult.text = "Error parsing XML"
        }
    }
}

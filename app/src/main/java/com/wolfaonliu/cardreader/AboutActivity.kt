package com.wolfaonliu.cardreader

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.wolfaonliu.cardreader.Util.getVersion
import java.util.*

class AboutActivity : AppCompatActivity(), OnItemClickListener {
    private var toolbar: Toolbar? = null
    private var mAdapter: AboutAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        toolbar = findViewById(R.id.about_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar?.setNavigationOnClickListener(View.OnClickListener { finish() })
        initData()
        initView()
    }

    private fun initData() {
        mAdapter = AboutAdapter(set, this)
    }

    private val set: ArrayList<Array<String>>
        get() {
            val settings = ArrayList<Array<String>>()
            val s = Array(4) { arrayOfNulls<String>(2) }
            s[0][0] = getString(R.string.version)
            s[0][1] = getVersion(this.applicationContext)
            s[1][0] = getString(R.string.developer)
            s[1][1] = "aoian"
            s[2][0] = getString(R.string.contact)
            s[2][1] = "wolfaonliuxgmailcom"
            s[3][0] = getString(R.string.github)
            s[3][1] = "https://github.com/2926295173/hut_helper"
            val sss = s[2][1]
            val array1 = arrayOf("版本号", getVersion(this.applicationContext), "开发者", "aoian")
            val x = mutableListOf(array1)
            //出现了为空的问题，暂时不导入整个列表组了
            //val x = mutableListOf<Array<String>>(*s)
            settings.addAll(x)

            return settings


        }

    private fun initView() {
        val mView = findViewById<ListView>(R.id.set_list)
        // 设置adapter
        mView.adapter = mAdapter
        mView.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
        val uri: Uri
        val intent: Intent
        when (position) {
            2 -> showEmailDialog()
            3 -> {
                uri = Uri.parse("https://github.com/2926295173/hut_helper")
                intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            else -> {}
        }
        Toast.makeText(this, "你点击了第" + position + "项", Toast.LENGTH_SHORT).show()
    }

    private fun showEmailDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.Report)
            .setMessage(R.string.report_atten)
        builder.setPositiveButton(R.string.yes) { dialog: DialogInterface?, _: Int ->
            val email = Intent(Intent.ACTION_SEND)
            email.type = "message/rfc822"
            email.putExtra(Intent.EXTRA_EMAIL, arrayOf("wolfaonliuxgmailcom"))
            email.putExtra(
                Intent.EXTRA_SUBJECT,
                getString(R.string.app_name) + getString(R.string.report)
            )
            startActivity(Intent.createChooser(email, getString(R.string.email_clint)))
        }
        builder.setNegativeButton(R.string.no, null)
        builder.show()
    }
}
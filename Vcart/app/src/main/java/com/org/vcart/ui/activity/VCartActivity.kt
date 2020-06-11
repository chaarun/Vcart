package com.org.vcart.ui.activity

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.org.vcart.R
import com.org.vcart.adapters.VCartAdapter
import com.org.vcart.utils.VCart
import kotlinx.android.synthetic.main.activity_vcart.*

class VCartActivity : AppCompatActivity() {

    lateinit var adapter: VCartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vcart)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val upArrow = ContextCompat.getDrawable(
            this,
            R.drawable.abc_ic_ab_back_material
        )
        upArrow?.setColorFilter(
            ContextCompat.getColor(
                this,
                R.color.colorPrimary
            ), PorterDuff.Mode.SRC_ATOP
        )
        supportActionBar?.setHomeAsUpIndicator(upArrow)

        adapter = VCartAdapter(this, VCart.getCart(this))
        adapter.notifyDataSetChanged()

        vcart_recyclerView.adapter = adapter
        vcart_recyclerView.layoutManager = LinearLayoutManager(this)

        var totalPrice = VCart.getCart(this).fold(0.toDouble()) { acc, cartItem ->
            acc + cartItem.quantity.times(cartItem.product.price!!.toDouble())
        }
        total_price.text = "${totalPrice}"

        proceed.setOnClickListener {
            Toast.makeText(this, "Completed..", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}

package com.org.vcart.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.org.vcart.R
import com.org.vcart.utils.CartItemQuantity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cart_list_item.view.*

class VCartAdapter(var context: Context, var cartItems: List<CartItemQuantity>) :
    RecyclerView.Adapter<VCartAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.cart_list_item, parent, false)
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindItem(cartItems[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(cartItem: CartItemQuantity) {
            itemView.product_name.text = cartItem.product.name
            itemView.product_price.text = "${cartItem.product.price}"
            itemView.product_quantity.text = cartItem.quantity.toString()
            Picasso.get().load(cartItem.product.image).fit().into(itemView.product_image)
        }
    }
}
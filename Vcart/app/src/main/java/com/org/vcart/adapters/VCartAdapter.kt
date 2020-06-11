package com.org.vcart.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.org.vcart.utils.CartItemQuantity
import com.org.vcart.R

import com.squareup.picasso.Picasso
import io.paperdb.Paper
import kotlinx.android.synthetic.main.cart_list_item.view.*
import kotlinx.android.synthetic.main.cart_list_item.view.product_image
import kotlinx.android.synthetic.main.cart_list_item.view.product_name
import kotlinx.android.synthetic.main.cart_list_item.view.product_price

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


//            Observable.create(ObservableOnSubscribe<MutableList<CartItemQuantity>> {
//                itemView.addToCart1.setOnClickListener { view ->
//                    val item = CartItemQuantity(cartItem.product)
//                    VCart.addItem(item,itemView.context)
//
//                    it.onNext(VCart.getCart(itemView.context))
//                }
//
//                itemView.removeItem1.setOnClickListener { view ->
//                    val item = CartItemQuantity(cartItem.product)
//                    VCart.removeItem(item,itemView.context)
//
//                    it.onNext(VCart.getCart(itemView.context))
//                }
//            }).subscribe { cart ->
//                var quantity = 0
//                cart.forEach { cartItem ->
//                    quantity += cartItem.quantity
//                }
//             //   (itemView.context as MainActivity).cart_size.text = quantity.toString()
//                Toast.makeText(itemView.context, "Cart size $quantity", Toast.LENGTH_SHORT).show()
//            }


        }





    }

}
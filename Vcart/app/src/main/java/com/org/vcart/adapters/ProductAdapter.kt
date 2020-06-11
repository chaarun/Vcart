package com.org.vcart.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.org.vcart.R
import com.org.vcart.ui.activity.MainActivity
import com.org.vcart.utils.CartItemQuantity
import com.org.vcart.utils.Product
import com.org.vcart.utils.VCart
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.product_row_item.view.*

class ProductAdapter(var context: Context, var products: List<Product> = arrayListOf()) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_row_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindProduct(products[position])
        (context as MainActivity).coordinator
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("CheckResult")
        fun bindProduct(product: Product) {
            itemView.product_name.text = product.name
            itemView.product_price.text = "${product.price.toString()}"
            Picasso.get().load(product.image).fit().into(itemView.product_image)

            Observable.create(ObservableOnSubscribe<MutableList<CartItemQuantity>> {
                itemView.addToCart.setOnClickListener { view ->
                    val item = CartItemQuantity(product)
                    VCart.addItem(item,itemView.context)
                    //notify users like toast
                    Snackbar.make(
                        (itemView.context as MainActivity).coordinator,
                        "${product.name} added to your cart",
                        Snackbar.LENGTH_LONG
                    ).show()
                    it.onNext(VCart.getCart(itemView.context))
                }

                itemView.removeItem.setOnClickListener { view ->
                    val item = CartItemQuantity(product)
                    VCart.removeItem(item,itemView.context)
                    Snackbar.make(
                        (itemView.context as MainActivity).coordinator,
                        "${product.name} removed from your cart",
                        Snackbar.LENGTH_LONG
                    ).show()
                    it.onNext(VCart.getCart(itemView.context))
                }
            }).subscribe { cart ->
                var quantity = 0
                cart.forEach { cartItem ->
                    quantity += cartItem.quantity
                }
                (itemView.context as MainActivity).cart_size.text = quantity.toString()
                Toast.makeText(itemView.context, "Cart size $quantity", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
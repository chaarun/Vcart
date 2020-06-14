package com.org.vcart.utils

import android.content.Context
import android.widget.Toast
import io.paperdb.Paper

class VCart {
    companion object {
        fun addItem(cartItem: CartItemQuantity, context: Context) {
            val cart = getCart(context)
            val targetItem = cart.singleOrNull { it.product.id == cartItem.product.id }
            if (targetItem == null) {
                cartItem.quantity++
                cart.add(cartItem)
            } else {
                targetItem.quantity++
            }
            saveCart(cart, context)
        }

            fun removeItem(cartItem: CartItemQuantity, context: Context) {
            val cart = getCart(context)
            val targetItem = cart.singleOrNull { it.product.id == cartItem.product.id }
            if (targetItem != null) {
                if (targetItem.quantity > 0) {
                    Toast.makeText(context, "Add more items", Toast.LENGTH_SHORT).show()
                    targetItem.quantity--
                } else {
                    cart.remove(targetItem)
                }
            }
            saveCart(cart, context)
        }

        fun saveCart(cart: MutableList<CartItemQuantity>, context: Context) {

//            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
//            val editor = prefs.edit()
//            val gson = Gson()
//            val json = gson.toJson(cart)
//            editor.putString("cart", json)
//            editor.apply()
            //   Logg.d("SaveCart"+json)
            Logg.d("SaveCart" + cart)
            Paper.book().write("cart", cart)
        }

        fun getCart(context: Context): MutableList<CartItemQuantity> {
//           val prefs = PreferenceManager.getDefaultSharedPreferences(context)
//            val emptyList = Gson().toJson(ArrayList<CartItemQuantity>())
//            Logg.d("getCart"+ emptyList)
//
//            return Gson().fromJson(
//                prefs.getString("cart", emptyList),
//                object : TypeToken<ArrayList<CartItemQuantity>>() {
//                }.type
//            )

            return Paper.book().read("cart", mutableListOf())
        }

        fun getVCartSize(context: Context): Int {
            var cartSize = 0
            getCart(context).forEach {
                cartSize += it.quantity;
            }
            return cartSize
        }
    }

}
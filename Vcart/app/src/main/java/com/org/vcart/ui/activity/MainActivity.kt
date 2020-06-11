package com.org.vcart.ui.activity


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.org.vcart.R
import com.org.vcart.adapters.ProductAdapter
import com.org.vcart.service.APIConfigURL
import com.org.vcart.service.APIService
import com.org.vcart.utils.Logg
import com.org.vcart.utils.Product
import com.org.vcart.utils.VCart
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var apiService: APIService
    private lateinit var productAdapter: ProductAdapter

    private var products = listOf<Product>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Paper.init(this)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar as Toolbar?)
        apiService = APIConfigURL.getRetrofitokHttpClient(this)
            .create(APIService::class.java)

        swipeRefreshLayout.setColorSchemeColors(
            ContextCompat.getColor(
                this,
                R.color.colorPrimary
            )
        )
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.setOnRefreshListener {
            getProductsData()
        }

//        val layoutManager = StaggeredGridLayoutManager(this, Lin)

        products_recyclerview.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        cart_size.text = VCart.getVCartSize(this)
            .toString()
        getProductsData()
        showCart.setOnClickListener {
            startActivity(Intent(this, VCartActivity::class.java))
        }
    }

    override fun onStop() {
        super.onStop()
        //Paper.book().destroy()
    }


    fun getProductsData() {
        apiService.getData().enqueue(object : retrofit2.Callback<List<Product>> {
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Logg.d("Failed::")
                print(t.message)
                Log.d("data error from api", t.message)
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                swipeRefreshLayout.isRefreshing = false
                // your code to get data from the list
                products = response.body()!!
                productAdapter = ProductAdapter(this@MainActivity, products)
                products_recyclerview.adapter = productAdapter
            }
        })
    }

}
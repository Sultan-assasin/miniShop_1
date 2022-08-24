package com.onay.minishop.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.onay.minishop.R
import com.onay.minishop.presentation.ShopItemActivity.Companion.newIntentAddItem
import com.onay.minishop.presentation.ShopItemActivity.Companion.newIntentEditItem


class MainActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinisedListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter
    private var shopItemContainer: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        shopItemContainer = findViewById(R.id.shop_item_container)
        setupRecyclerView()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //здесь мы подписались на LiveData , а внутри будет реализация отоброжения элеентов
        viewModel.shopList.observe(this) {
            shopListAdapter.submitList(it)  // установка нового списка  в адаптер
        }


        val buttonAddItem = findViewById<FloatingActionButton>(R.id.button_add_shop_item)
        buttonAddItem.setOnClickListener() {
            if (isOnePaneMode()) {
                val intent = newIntentAddItem(this)
                startActivity(intent)

            } else {
                lauchFragment(ShopItemFragment.newInstanceAddItem())
            }
        }
    }

    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)

        with(rvShopList) {
            shopListAdapter = ShopListAdapter()
            adapter = shopListAdapter // баг был исправлен
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_ENABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_DISABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )

        }
        setupLongClickListener()
        setupClickListener()
        enableSwipeToDeleteAndUndo(rvShopList)

    }

    private fun isOnePaneMode(): Boolean {
        return shopItemContainer == null
    }

    private fun lauchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.shop_item_container, fragment) // replace заменяет фрагмент
            .addToBackStack(null)
            .commit()
    }

    private fun setupLongClickListener() {
        shopListAdapter.onShopItemLongClickListener = {
            viewModel.changeEnableState(it)


        }
    }

    private fun setupClickListener() {
        shopListAdapter.onShopItemClickListener = {
            if (isOnePaneMode()) {
                val intent = newIntentEditItem(this, it.id)
                startActivity(intent)
                lauchFragment(ShopItemFragment.newInstanceEditMode(it.id))
            } else {
                lauchFragment(ShopItemFragment.newInstanceEditMode(it.id))
            }

        }
    }

    private fun enableSwipeToDeleteAndUndo(recyclerView: RecyclerView) {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item =
                    shopListAdapter.currentList[viewHolder.adapterPosition] // получили обьект
                viewModel.deleteShopItem(item)

            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    override fun onEditingFinisedListener() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }

}


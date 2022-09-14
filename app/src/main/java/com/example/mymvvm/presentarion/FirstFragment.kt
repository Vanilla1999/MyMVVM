package com.example.mymvvm.presentarion

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mymvvm.MainActivity
import com.example.mymvvm.R
import com.example.mymvvm.databinding.FragmentFirstBinding
import com.example.mymvvm.di.DaggerFirstFragmentComponent
import com.example.mymvvm.di.FirstFragmentComponent
import com.example.mymvvm.domain.FactoryFirstFragmentViewModel
import com.example.mymvvm.domain.FirstFragmentViewModel
import com.example.mymvvm.presentarion.adapter.ClickListenerForAdapter
import com.example.mymvvm.presentarion.adapter.ShopListAdapter
import com.sumin.shoppinglist.domain.ShopItem
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(R.layout.fragment_first) {
    @Inject
    lateinit var factory: FactoryFirstFragmentViewModel
    private val viewModelFirstFragment by viewModels<FirstFragmentViewModel> { factory }
    private val binding: FragmentFirstBinding by viewBinding()

    private lateinit var adapter: ShopListAdapter
    lateinit var fragmentComponent: FirstFragmentComponent
    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentComponent = DaggerFirstFragmentComponent.factory()
            .create((requireActivity() as MainActivity).activityComponent)
        fragmentComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.buttonFlex2.setOnClickListener {
            // presenter.changeList()
        }
        initFlow()
        initRecyclerView()
    }

    private fun initFlow() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModelFirstFragment.sharedFlowShopitem.collect {
                adapter.submitList(it)
            }
        }
    }

    private fun initRecyclerView() {
        adapter = ShopListAdapter {
            editShopitem(it)
        }
        binding.rvShopList.adapter = adapter
    }

    private fun editShopitem(shopItem: ShopItem) {
        Toast.makeText(
            context,
            "Смена состояния" + shopItem.id,
            Toast.LENGTH_SHORT
        ).show()
        viewModelFirstFragment.editShopItem(shopItem)
    }


}

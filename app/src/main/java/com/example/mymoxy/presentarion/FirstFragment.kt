package com.example.mymoxy.presentarion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mymoxy.R
import com.example.mymoxy.databinding.FragmentFirstBinding
import com.example.mymoxy.domain.FirstPresenter
import com.sumin.shoppinglist.domain.ShopItem
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : MvpAppCompatFragment(R.layout.fragment_first), FirstInterface {
    @InjectPresenter
    lateinit var presenter: FirstPresenter

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: ShopListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.buttonFlex.setOnClickListener {
            presenter.recolorImage()
        }
        createAdapter()

    }

    private fun createAdapter(){
        adapter = ShopListAdapter {
            Toast.makeText(
                context,
                "Flex",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.rvShopList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onInitImage() {
        binding.image.setImageResource(R.drawable.ic_launcher_foreground)
    }

    override fun onRecolorImage() {
        binding.image.setImageResource(R.drawable.ic_load_image)
    }

    override fun onInitShopList(stores: List<ShopItem>) {
        adapter.setStores(stores)
    }
}
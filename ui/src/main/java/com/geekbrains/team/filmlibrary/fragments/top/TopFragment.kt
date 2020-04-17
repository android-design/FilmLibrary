package com.geekbrains.team.filmlibrary.fragments.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.adapters.MyTabAdapter
import com.geekbrains.team.filmlibrary.util.DiffUtilsCallback
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.top_fragment.*
import java.util.*
import javax.inject.Inject

class TopFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<TopFragmentViewModel> { viewModelFactory }

    private val strings = ArrayList<String>()
    private var myTabAdapter: MyTabAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        strings.add("Фильмы")
        strings.add("Сериалы")

        viewModel.fragments.observe(viewLifecycleOwner, Observer { data ->
            data?.let {
                myTabAdapter = MyTabAdapter(childFragmentManager, it, strings)
                viewPager.apply {
                    adapter = myTabAdapter
                }

                tabs.setupWithViewPager(viewPager)
            }
        })

        viewModel.loadData()
    }
}

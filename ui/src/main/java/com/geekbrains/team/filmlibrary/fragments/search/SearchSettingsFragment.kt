//package com.geekbrains.team.filmlibrary.fragments.search
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.geekbrains.team.filmlibrary.MainActivity
//import com.geekbrains.team.filmlibrary.R
//import com.geekbrains.team.filmlibrary.adapters.ItemsAdapter
//import com.geekbrains.team.filmlibrary.adapters.OnItemSelectedListener
//import com.geekbrains.team.filmlibrary.util.DiffUtilsCallback
//import dagger.android.support.DaggerFragment
//import kotlinx.android.synthetic.main.search_settings_inner_fragment.*
//import javax.inject.Inject
//
//class SearchSettingsFragment : DaggerFragment(), OnItemSelectedListener {
//
//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory
//
//    private val viewModel by viewModels<SearchViewModel>({ activity as MainActivity }) { viewModelFactory }
//
//    private val genresAdapter by lazy {
//        ItemsAdapter<DateSettings>(
//            layout = R.layout.settings_item,
//            clickListener = this
//        )
//    }
//
//    private val genresFromServer = listOf(
//        DateSettings(14, "Fantasy"),
//        DateSettings(36, "History"),
//        DateSettings(27, "Horror"),
//        DateSettings(10749, "Romance"),
//        DateSettings(10752, "War"),
//        DateSettings(37, "Western")
//    )
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.search_settings_inner_fragment, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        genres_settings_rv.apply {
//            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
//            adapter = genresAdapter
//
//            update()
//        }
//    }
//
//    private fun update() {
////        genresFromServer.map {
////            it.isChecked = viewModel.genresFiltersValue.contains(it.id)
////        }
////
////        val sortedList = genresFromServer.sortedWith(Comparator { o1, o2 ->
////            o2.isChecked.compareTo(
////                o1.isChecked
////            )
////        })
////
////        val diffUtilCallback = DiffUtilsCallback(genresAdapter.data, sortedList)
////        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
////
////        genresAdapter.update(sortedList)
////
////        val recyclerViewState = genres_settings_rv.layoutManager?.onSaveInstanceState()
////        diffResult.dispatchUpdatesTo(genresAdapter)
////        genres_settings_rv.layoutManager?.onRestoreInstanceState(recyclerViewState)
//    }
//
//    override fun addId(id: Int) {
////        viewModel.genresFiltersValue.add(id)
//        update()
//    }
//
//    override fun removeId(id: Int) {
////        viewModel.genresFiltersValue.remove(id)
//        update()
//    }
//}
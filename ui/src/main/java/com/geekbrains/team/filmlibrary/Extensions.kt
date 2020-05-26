package com.geekbrains.team.filmlibrary

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.text.SimpleDateFormat
import java.util.*

fun Date.parseToShortFormat(): String {
    val outputDateFormat = SimpleDateFormat("d MMM", Locale.getDefault())

    return outputDateFormat.format(this)
}

fun Date.parseToYear(): String {
    val outputDateFormat = SimpleDateFormat("yyyy", Locale.getDefault())

    return outputDateFormat.format(this)
}

fun Date.parseToNormalFormat(): String {
    val outputDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    return outputDateFormat.format(this)
}

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun RecyclerView.addOnScrollListenerPagination(
    layoutManager: LinearLayoutManager,
    callBack: () -> Unit
) {
    clearOnScrollListeners()

    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val totalItemCount = layoutManager.itemCount
            val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

            if (lastVisibleItem + Const.PRELOAD_FROM_SERVER_ITEMS_COUNT == totalItemCount) {
                callBack.invoke()
            }
        }
    })
}

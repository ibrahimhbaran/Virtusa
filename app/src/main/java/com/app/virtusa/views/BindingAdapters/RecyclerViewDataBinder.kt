package com.app.virtusa.views.BindingAdapters

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.app.virtusa.data.model.Album
import com.app.virtusa.views.AlbumAdapter

/**
 * Sets an adapter to a RecyclerView (to be used in view with one RecyclerView)
 * @param view the RecyclerView on which to set the adapter
 * @param adapter the adapter to set to the RecyclerView
 */
@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: AlbumAdapter) {
    view.adapter = adapter
}

/**
 * Sets a LayoutManager to a RecyclerView (to be used in view with one RecyclerView)
 * @param view the RecyclerView on which to set the LayoutManager
 * @param layoutManager the LayoutManager to set to the RecyclerView
 */
@BindingAdapter("layoutManager")
fun setLayoutManager(view: RecyclerView, layoutManager: RecyclerView.LayoutManager) {
    view.layoutManager = layoutManager
}

/**
 * Adds a DividerItemDecoration to a RecyclerView (to be used in view with one RecyclerView)
 * @param view the RecyclerView on which to set the DividerItemDecoration
 * @param dividerItemDecoration the DividerItemDecoration to set to the RecyclerView
 */
@BindingAdapter("dividerItemDecoration")
fun setDividerItemDecoration(view: RecyclerView, dividerItemDecoration: RecyclerView.ItemDecoration) {
    view.addItemDecoration(dividerItemDecoration)
}

/**
 *  updates  albums on RecylerView
 *  @param view view that albums listed on
 *  @param albums albums that coming from viewModel
 */

@BindingAdapter("albums")
fun setAlbums(view: RecyclerView, albums: List<Album>?) {

    with(view.adapter as AlbumAdapter) {
        updateAlbums(albums)
    }
}

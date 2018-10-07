package com.app.virtusa.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.app.virtusa.data.model.Album
import com.app.virtusa.data.source.AlbumsRepository
import com.app.virtusa.utils.EspressoIdlingResource
import com.app.virtusa.utils.Status
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class AlbumViewModel : ViewModel() {

    private val disposable = CompositeDisposable()

    val responseStatus = ResponseStatus()

    val response = MutableLiveData<List<Album>>()


    override fun onCleared() {
        disposable.clear()
    }


    fun loadAlbums(repository: AlbumsRepository) {

        EspressoIdlingResource.increment()

        disposable.add(repository.getAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe
                {
                    responseStatus.status = Status.LOADING
                }
                .subscribe(
                        {
                            response.value = it
                            responseStatus.status = Status.SUCCESS
                            // we use this for UI testing ,Espresso normally doesn't know if we are loading data at background .
                            if (!EspressoIdlingResource.idlingResource.isIdleNow) {
                                EspressoIdlingResource.decrement() // Set app as idle.
                            }

                        },
                        {
                            responseStatus.status = Status.ERROR
                        }))

    }


}
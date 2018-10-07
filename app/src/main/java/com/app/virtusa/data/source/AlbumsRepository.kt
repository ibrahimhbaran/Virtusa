package com.app.virtusa.data.source


/**
 * Repository class for fetching data from remote service
 */
class AlbumsRepository {

    fun getAlbums() = NetworkApi.create().getAlbums()


    companion object {

        private var INSTANCE: AlbumsRepository? = null


        @JvmStatic
        fun getInstance(): AlbumsRepository {

            if (INSTANCE == null) {

                synchronized(AlbumsRepository::class.java) {

                    INSTANCE = AlbumsRepository()

                }


            }
            return INSTANCE!!
        }


    }

}
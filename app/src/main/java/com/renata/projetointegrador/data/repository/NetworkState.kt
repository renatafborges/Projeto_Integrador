package com.renata.projetointegrador.data.repository

enum class  Status {
    RUNNING,
    SUCESS,
    FAILED
}

class NetworkState (val status: Status, val msg: String) {

    companion object {

        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState

        init{
            LOADED = NetworkState(Status.SUCESS, "Sucess")

            LOADING = NetworkState(Status.RUNNING, "Running")

            ERROR = NetworkState(Status.FAILED, "Something went wrong" )
        }
    }
}
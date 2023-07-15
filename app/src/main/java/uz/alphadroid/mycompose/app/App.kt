package uz.alphadroid.mycompose.app

import android.app.Application
import android.content.Context
import uz.alphadroid.mycompose.data.MainDB

class App : Application() {
    companion object{
        var   context: Context? =null
    }

    override fun onCreate() {
        super.onCreate()
        MainDB.init(this)
        context=this
    }
}
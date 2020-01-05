package util

import android.os.AsyncTask

class Task<T>(
    val onComplet:(T)->Unit,
    val onError:(Exception)->Unit

) : AsyncTask<() -> T, Void, T>() {
    var ex:Exception? = null
    override fun doInBackground(vararg params: (() -> T)?): T? {
        try {
            return params[0]!!.invoke()
        }catch (e:Exception){
            this.ex = e
            return null
        }

    }

    override fun onPostExecute(result: T) {
        if(ex==null){
            this.onComplet(result)
        }else{
            this.onError(this.ex!!)
        }
    }


}
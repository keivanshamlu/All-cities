package com.shamlou.data_local.fileReader

import android.content.Context

/**
 * reads file from assets and
 * returns string version of file
 */
class ReadFileFromAssets(private val applicationContext: Context) {

    fun readFile(fileName: String): String {
        return applicationContext.assets.open(fileName).run {
            ByteArray(available()).let {
                read(it)
                close()
                String(it, charset("UTF-8"))
            }
        }
    }
}
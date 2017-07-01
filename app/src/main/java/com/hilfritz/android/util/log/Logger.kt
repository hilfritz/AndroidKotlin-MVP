package com.hilfritz.samplekotlin.util.log

import android.util.Log

/**
 * Created by hilfritz on 30/3/16.
 * @see http://stackoverflow.com/questions/7606077/how-to-display-long-messages-in-logcat
 * this logger class helps prevent string being cut when string is too long
 */
object Logger {

    fun d(TAG: String, message: String) {
        Logger.log(Log.DEBUG,TAG, message)
    }

    fun i(TAG: String, message: String) {
        Logger.log(Log.INFO,TAG, message)
    }

    fun w(TAG: String, message: String) {
        Logger.log(Log.WARN,TAG, message)
    }

    fun e(TAG: String, message: String) {
        Logger.log(Log.ERROR,TAG, message)
    }

    /**
     * This one sends log to console and to file
     * @param priority
     * *
     * @param TAG
     * *
     * @param message
     */
    private fun log(priority: Int, TAG: String, message: String) {
        val maxLogSize = 1000
        for (i in 0..message.length / maxLogSize) {
            val start = i * maxLogSize
            var end = (i + 1) * maxLogSize
            end = if (end > message.length) message.length else end

            val msg = message.substring(start, end)
            when (priority) {
                Log.DEBUG -> Log.d(TAG, msg)
                Log.INFO -> Log.i(TAG, msg)
                Log.WARN -> Log.w(TAG, msg)
                Log.ERROR -> Log.e(TAG, msg)
            }
            System.out.println(msg)
        }
    }

}

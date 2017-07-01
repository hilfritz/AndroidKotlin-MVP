package com.hilfritz.android.util

import com.hilfritz.android.MyApplication
import java.io.File

/**
 * Created by Hilfritz Camallere on 24/6/17.
 *
 */
/**
 * Created by Hilfritz Camallere on 21/3/16.
 */
class ImageCache(internal var myApplication: MyApplication) {
    internal var cacheDir: File? = null
    internal var imageDir: File? = null
    internal var reportingOfficerDir: File? = null
    internal var payslipDir: File? = null

    /*
    init {
        instance = this
        initializeDirectories(myApplication)
    }

    fun initializeDirectories(myApplication: MyApplication) {
        //cacheDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        //cacheDir = Environment.getExternalStorageDirectory().getAbsoluteFile();
        val filesDir = myApplication.getFilesDir()

        //File cacheDir = MyApplication.getInstance().getDir("HerdMe", Context.MODE_PRIVATE);
        val cacheDir = File(filesDir, "HerdMe")


        //File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        //File externalStoragePublicDirectory = Environment.getDownloadCacheDirectory();
        //File externalStoragePublicDirectory = Environment.getDataDirectory(); //NOT WORKING
        val externalStoragePublicDirectory = myApplication.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) //this directory will be deleted when the app is uninstalled
        //File externalStoragePublicDirectory = filesDir;

        val external = File(externalStoragePublicDirectory, "HerdMe")

        if (external.exists() == false) {
            if (external.mkdirs()) {
                Log.d(TAG, "initializeDirectories(): external directory created")
            } else {
                Log.d(TAG, "initializeDirectories(): external directory cannot be created")
            }
        }

        if (cacheDir.exists() == false) {
            if (cacheDir.mkdirs()) {
                Log.d(TAG, "initializeDirectories(): cache directory created")
            } else {
                Log.d(TAG, "initializeDirectories(): cache directory cannot be created")
            }
        }

        imageDir = File(cacheDir, DIRECTORY_IMAGE_CACHE_DIR)
        if (imageDir!!.exists() == false) {
            if (imageDir!!.mkdirs()) {
                Log.d(TAG, "initializeDirectories(): imageDir directory created")
            } else {
                Log.d(TAG, "initializeDirectories(): imageDir directory cannot be created")
            }
        }

        reportingOfficerDir = File(imageDir, REPORTING_OFFICER_IMAGE_CACHE_DIR)
        if (reportingOfficerDir.exists() == false) {
            if (reportingOfficerDir.mkdirs()) {
                Log.d(TAG, "initializeDirectories(): reportingOfficerDir directory created")
            } else {
                Log.d(TAG, "initializeDirectories(): reportingOfficerDir directory cannot be created")
            }
        }

        payslipDir = File(external, PAYSLIP_DIR)
        if (payslipDir.exists() == false) {
            if (payslipDir.mkdirs()) {
                Log.d(TAG, "initializeDirectories(): payslip directory created")
            } else {
                Log.d(TAG, "initializeDirectories(): payslip directory cannot be created")
            }
        }
    }


    fun getReportingOfficerCacheDirectory(myApplication: MyApplication): File {
        initializeDirectories(myApplication)
        return reportingOfficerDir
    }


    fun getPayslipDir(myApplication: MyApplication): File {
        initializeDirectories(myApplication)
        return payslipDir
    }

    fun setPayslipDir(payslipDir: File) {
        this.payslipDir = payslipDir
    }

    fun reset() {
        try {
            FileUtils.deleteDirectory(payslipDir)
            FileUtils.deleteDirectory(imageDir)
            FileUtils.deleteDirectory(reportingOfficerDir)
            //initializeDirectories();
        } catch (e: IOException) {
            Log.d(TAG, "reset: exception:" + e)
            e.printStackTrace()
        }

    }

    companion object {
        private val TAG = "ImageCache"
        val DIRECTORY_IMAGE_CACHE_DIR: java.lang.String = "image"
        val REPORTING_OFFICER_IMAGE_CACHE_DIR: java.lang.String = "reportingOfficer"
        val PAYSLIP_DIR: java.lang.String = ".payslip"
        // Singleton instance
        var instance: ImageCache? = null
    }
    */
}
package iqro.mobile.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.work.*
import iqro.mobile.workmanager.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    val TAG = "TAG"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
//            .setRequiresCharging(true)
            .build()


        val periodic = PeriodicWorkRequestBuilder<MyWorker>(20, TimeUnit.SECONDS).build()
        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("periodic", ExistingPeriodicWorkPolicy.REPLACE, periodic)




        binding.oneTimeWorkBtn.setOnClickListener {
            WorkManager.getInstance(this)
                .enqueueUniquePeriodicWork("periodic", ExistingPeriodicWorkPolicy.REPLACE, periodic)
            WorkManager.getInstance(this).getWorkInfoByIdLiveData(periodic.id).observeForever {
                val process = it.progress
                val number = process.getInt("progress",0)
                Log.d(TAG, "onCreate: $number")
            }
        }
    }
}
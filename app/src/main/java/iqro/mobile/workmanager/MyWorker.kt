package iqro.mobile.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

/**
 *Created by Zohidjon Akbarov
 */

const val TAG = "TAG"
class MyWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        val randomNumber = Random.nextInt(100)


        repeat(100){
            setProgress(workDataOf("progress" to it+1))
            delay(50)
        }
        return if (randomNumber % 2 == 0)
            Result.success(workDataOf("output" to "Photo downloaded successfully"))
        else Result.failure()
    }
}
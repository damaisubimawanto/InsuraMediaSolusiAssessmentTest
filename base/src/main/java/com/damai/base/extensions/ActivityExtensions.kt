package com.damai.base.extensions

import android.content.Context
import android.content.res.Resources
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import com.damai.base.utils.Event
import com.damai.base.utils.EventObserver
import kotlin.math.roundToInt

/**
 * Created by damai007 on 18/August/2023
 */

fun <T> FragmentActivity.observe(
    liveData: LiveData<T>,
    action: (t: T) -> Unit
) {
    liveData.observe(this) { it?.let { t -> action(t) } }
}

@JvmName("observeEvent")
fun <T> FragmentActivity.observe(
    liveData: LiveData<Event<T>>,
    observer: EventObserver<T>
) {
    liveData.observe(this, observer)
}

fun Context.showShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.toPx(givenInt: Int) = (givenInt * resources.displayMetrics.density).roundToInt()

fun getScreenHeight() = Resources.getSystem().displayMetrics.heightPixels
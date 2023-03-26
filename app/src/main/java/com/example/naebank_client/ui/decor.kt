package com.example.naebank_client.ui

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

@set:BindingAdapter("visibleOrGone")
inline var View.visibleOrGone
  get() = visibility == View.VISIBLE
  set(value) {
    visibility = if (value) View.VISIBLE else View.GONE
  }

@set:BindingAdapter("visible")
inline var View.visible
  get() = visibility == View.VISIBLE
  set(value) {
    visibility = if (value) View.VISIBLE else View.INVISIBLE
  }

@set:BindingAdapter("gone")
inline var View.gone
  get() = visibility == View.GONE
  set(value) {
    visibility = if (value) View.GONE else View.VISIBLE
  }

@set:BindingAdapter("invisible")
inline var View.invisible
  get() = visibility == View.INVISIBLE
  set(value) {
    visibility = if (value) View.INVISIBLE else View.VISIBLE
  }

@BindingConversion
fun convertBooleanToVisibility(visible: Boolean) = if (visible) View.VISIBLE else View.GONE

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
  this.addTextChangedListener(object : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(editable: Editable?) {
      afterTextChanged.invoke(editable.toString())
    }
  })
}

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
  observe(lifecycleOwner, object : Observer<T> {
    override fun onChanged(value: T) {
      observer.onChanged(value)
      removeObserver(this)
    }
  })
}
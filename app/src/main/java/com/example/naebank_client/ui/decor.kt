package com.example.naebank_client.ui

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion

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
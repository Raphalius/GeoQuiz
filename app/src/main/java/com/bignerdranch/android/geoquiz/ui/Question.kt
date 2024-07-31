package com.bignerdranch.android.geoquiz.ui

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean) {}

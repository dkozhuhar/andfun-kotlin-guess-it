package com.example.android.guesstheword.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ScoreViewModel(finalScore:Int): ViewModel() {
    private val _score = MutableLiveData<Int>()
    private val _eventPlayAgain = MutableLiveData<Boolean>()
    init {
        _score.value = finalScore
        _eventPlayAgain.value = false
    }
    val score : LiveData<Int>
        get() = _score
    val eventPlayAgain : LiveData<Boolean>
        get() = _eventPlayAgain

    fun onPlayAgain(){
        _eventPlayAgain.value = true
    }
}

class ScoreViewModelFactory(private val finalScore: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            // TODO Construct and return the ScoreViewModel
            return ScoreViewModel(finalScore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
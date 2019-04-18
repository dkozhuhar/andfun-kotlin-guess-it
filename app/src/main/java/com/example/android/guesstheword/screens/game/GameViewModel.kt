package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

// These represent different important times
// This is when the game is over
val DONE = 0L
// This is the number of milliseconds in a second
val ONE_SECOND = 1000L
// This is the total time of the game
val COUNTDOWN_TIME = 6000L

class GameViewModel : ViewModel() {
    // The current _word
    private val _word = MutableLiveData<String>()

    // The current _score
    private val _score = MutableLiveData<Int>()

    private val _currentTime = MutableLiveData<Long>()

    private val _eventGameFinish = MutableLiveData<Boolean>()

    private val timer = object: CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
        override fun onFinish() {
            _eventGameFinish.value = true
        }

        override fun onTick(p0: Long) {
            _currentTime.value = p0
        }
    }

    init {
        _eventGameFinish.value = false
        _score.value = 0
        _word.value = ""
        resetList()
        nextWord()
        Timber.i("GameViewModel created")
        timer.start()
    }
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish
    val score: LiveData<Int>
        get() = _score
    val word: LiveData<String>
        get() = _word
    val currentTime: LiveData<Long>
        get() = _currentTime
    // The list of words - the front of the list is the next _word to guess
    private lateinit var wordList: MutableList<String>

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /** Methods for buttons presses **/

    fun onSkip() {
        _score.value = _score.value?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = _score.value?.plus(1)
        nextWord()
    }

    /**
     * Moves to the next _word in the list
     */
    private fun nextWord() {
        //Select and remove a _word from the list
        if (wordList.isEmpty()) {
            //gameFinished()
            //_eventGameFinish.value = true
            resetList()
        } else {
            _word.value = wordList.removeAt(0)
        }
    }

    fun onGameFinishedComplete (){
        _eventGameFinish.value = false
    }


    override fun onCleared() {
        super.onCleared()
        Timber.i("GameViewModel cleared")
        timer.cancel()
    }
}
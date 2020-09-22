package com.example.testrecycleview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object FakeListText {
    private val listText = ArrayList<TextItem>()
    private val listTextLiveData = MutableLiveData<List<TextItem>>()

    init {
        listTextLiveData.postValue(listText)
        addItem("Text 1")
        addItem("Text 2")
        addItem("Text 3")
        addItem("Text 4")
    }

    fun getListText(): LiveData<List<TextItem>> {
        return listTextLiveData
    }

    fun addItem(text: String) {
        listText.add(TextItem(text))
        listTextLiveData.postValue(listText)
    }

    fun removeItem(text: String) {
        val tmp = ArrayList<TextItem>()
        listText.forEach {
            if(it.text == text){
                tmp.add(it)
            }
        }
        tmp.forEach {
            listText.remove(it)
        }
        listTextLiveData.postValue(listText)
    }


}
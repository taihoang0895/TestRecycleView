package com.example.testrecycleview

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class ListTextViewModel : ViewModel() {
    private val listTextItemView = ArrayList<TextItemView>()
    fun getListText(): LiveData<List<TextItemView>> {
        return Transformations.map(FakeListText.getListText()) { listTextItems ->

            // it is first time
            listTextItems.forEach {
                listTextItemView.add(TextItemView(it))
            }

            listTextItemView
        }
    }

    fun selectItem(pos: Int) : List<TextItemView>{
        val copy = listTextItemView.get(pos).copy()
        copy.checked = !copy.checked
        listTextItemView[pos] = copy
        return listTextItemView
    }

}
package com.example.mortycharacters.characters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mortycharacters.R
import kotlinx.android.synthetic.main.activity_character_list.*

class CharacterListActivity : AppCompatActivity() {

    private val epoxyController = CharacterPagedListEpoxyController()
    private val viewModel: CharactersViewModel by lazy {
        ViewModelProvider(this).get(CharactersViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        viewModel.charactersPagedListLiveData.observe(this){ pagedList ->
            epoxyController.submitList(pagedList)
        }
        epoxyRecyclerView_character_list.setController(epoxyController)
    }
}
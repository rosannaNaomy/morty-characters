package com.example.mortycharacters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mortycharacters.epoxy.CharacterDetailsEpoxyController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }
    private val epoxyController = CharacterDetailsEpoxyController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel.characterByIdLiveData.observe(this){ character ->
            epoxyController.character = character
            if(character == null){
                Toast.makeText(this@MainActivity, "Unsuccessful network call.", Toast.LENGTH_SHORT).show()
                return@observe
            }
        }

        val id = intent.getIntExtra(Constants.INTENT_EXTRA_CHARACTER_ID, 1)
        viewModel.refreshCharacter(characterId = id)

        val epoxyRecyclerV = epoxyRecyclerView
        epoxyRecyclerV.setControllerAndBuildModels(epoxyController)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
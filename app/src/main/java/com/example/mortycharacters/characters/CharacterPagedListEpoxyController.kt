package com.example.mortycharacters.characters

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.mortycharacters.R
import com.example.mortycharacters.databinding.ModelCharacterListItemBinding
import com.example.mortycharacters.databinding.ModelCharacterListTitleBinding
import com.example.mortycharacters.epoxy.LoadingEpoxyModel
import com.example.mortycharacters.epoxy.ViewBindingKotlinModel
import com.example.mortycharacters.network.response.CharacterResponse
import com.squareup.picasso.Picasso
import java.util.*

class CharacterPagedListEpoxyController(
    val onCharacterSelected: (Int) -> Unit
): PagedListEpoxyController<CharacterResponse>() {

    override fun buildItemModel(
        currentPosition: Int,
        item: CharacterResponse?
    ): EpoxyModel<*> {
        return CharacterGridItemEpoxyModel(
            characterId = item!!.id,
            imageUrl = item.image,
            name = item.name,
            onCharacterSelected = onCharacterSelected
        ).id(item.id)
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        if(models.isEmpty()){
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        CharacterGridTitleEpoxyModel("Main Family")
            .id("main_family_header")
            .addTo(this)

        super.addModels(models.subList(0, 5))

        (models.subList(5, models.size) as List<CharacterGridItemEpoxyModel>).groupBy {
            it.name[0].uppercaseChar()
        }.forEach{ mapEntry ->
            val character = mapEntry.key.toString().uppercase(Locale.US)
            CharacterGridTitleEpoxyModel(title = character)
                .id(character)
                .addTo(this)

            super.addModels(mapEntry.value)
        }
    }

    data class CharacterGridItemEpoxyModel(
       val characterId: Int,
       val imageUrl: String,
       val name: String,
       val onCharacterSelected: (Int) -> Unit
    ): ViewBindingKotlinModel<ModelCharacterListItemBinding>(R.layout.model_character_list_item){

        override fun ModelCharacterListItemBinding.bind() {
            Picasso.get().load(imageUrl).into(characterImageview)
            characterNameTextview.text = name

            root.setOnClickListener {
                onCharacterSelected(characterId)
            }
        }
    }

    data class CharacterGridTitleEpoxyModel(
        val title: String
    ): ViewBindingKotlinModel<ModelCharacterListTitleBinding>(R.layout.model_character_list_title){

        override fun ModelCharacterListTitleBinding.bind() {
            headerListTextview.text = title
        }

        override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
            return totalSpanCount
        }
    }
}
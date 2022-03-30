package com.example.mortycharacters.characters

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.example.mortycharacters.R
import com.example.mortycharacters.databinding.ModelCharacterListItemBinding
import com.example.mortycharacters.epoxy.ViewBindingKotlinModel
import com.example.mortycharacters.model.MortyCharacter
import com.squareup.picasso.Picasso

class CharacterPagedListEpoxyController: PagedListEpoxyController<MortyCharacter>() {

    override fun buildItemModel(
        currentPosition: Int,
        item: MortyCharacter?
    ): EpoxyModel<*> {
        return CharacterGridItemEpoxyModel(item!!.image, item.name).id(item.id)
    }

    data class CharacterGridItemEpoxyModel(
       val imageUrl: String,
       val name: String
    ): ViewBindingKotlinModel<ModelCharacterListItemBinding>(R.layout.model_character_list_item){

        override fun ModelCharacterListItemBinding.bind() {
            Picasso.get().load(imageUrl).into(characterImageview)
            characterNameTextview.text = name
        }
    }
}
package com.example.mortycharacters.epoxy

import com.airbnb.epoxy.EpoxyController
import com.example.mortycharacters.R
import com.example.mortycharacters.databinding.ModelCharacterDetailsDataPointBinding
import com.example.mortycharacters.databinding.ModelCharacterDetailsHeaderBinding
import com.example.mortycharacters.databinding.ModelCharacterDetailsImageBinding
import com.example.mortycharacters.model.MortyCharacter
import com.squareup.picasso.Picasso

class CharacterDetailsEpoxyController: EpoxyController(){

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field){
                requestModelBuild()
            }
        }
    var characterResponse: MortyCharacter? = null
        set(value) {
            field = value
            if (field != null){
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {

        if (isLoading){
            //show loading state
            LoadingEpoxyModel().id("laoding").addTo(this)
            return
        }

        if(characterResponse == null){
            return
        }

        //add header model
        //add image model
        //add data points model(S)
        HeaderEpoxyModel(
            name = characterResponse!!.name,
            gender = characterResponse!!.gender,
            status = characterResponse!!.status
        ).id("header").addTo(this)

        ImageEpoxyModel(
            imageUrl = characterResponse!!.image
        ).id("image").addTo(this)

        DataPointEpoxyModel(
            title = "Origin",
            description = characterResponse!!.origin.name
        ).id("data_point_origin").addTo(this)

        DataPointEpoxyModel(
            title = "Species",
            description = characterResponse!!.species
        ).id("data_point_species").addTo(this)

    }

    data class HeaderEpoxyModel(
        val name: String,
        val gender: String,
        val status: String
    ): ViewBindingKotlinModel<ModelCharacterDetailsHeaderBinding>(R.layout.model_character_details_header) {

        override fun ModelCharacterDetailsHeaderBinding.bind() {
            nameTextview.text = name
            aliveTextview.text = status

            if (gender.equals("male", true)) {
                genderImageview.setImageResource(R.drawable.ic_baseline_male_24)
            } else {
                genderImageview.setImageResource(R.drawable.ic_baseline_female_24)
            }
        }
    }

    data class ImageEpoxyModel(
        val imageUrl: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsImageBinding>(R.layout.model_character_details_image) {

        override fun ModelCharacterDetailsImageBinding.bind() {
            Picasso.get().load(imageUrl).into(headerImageView)
        }
    }

    data class DataPointEpoxyModel(
        val title: String,
        val description: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsDataPointBinding>(R.layout.model_character_details_data_point) {

        override fun ModelCharacterDetailsDataPointBinding.bind() {
            titleTextView.text = title
            labelTextView.text = description
        }
    }


}
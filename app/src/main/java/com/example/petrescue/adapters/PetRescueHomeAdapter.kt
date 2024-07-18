package com.example.petrescue.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.petrescue.databinding.PetRescueAnimalItemBinding
import com.example.petrescue.model.BreedsImagesModel

class PetRescueHomeAdapter: RecyclerView.Adapter<PetRescueHomeAdapter.PetRescueHomeViewHolder>() {

    private var data: MutableList<String> = mutableListOf()
    private var breed: String = ""
    private var isLoading = true

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PetRescueHomeAdapter.PetRescueHomeViewHolder {
        val binding = PetRescueAnimalItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return PetRescueHomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PetRescueHomeAdapter.PetRescueHomeViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class PetRescueHomeViewHolder(val binding: PetRescueAnimalItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: String){
            with(binding){
                if(isLoading){
                    petRescueAnimalItemContainer.isVisible = false
                    petRescueAnimalItemShimmer.startShimmer()
                }else{
                    petRescueAnimalItemShimmer.apply {
                        visibility = View.GONE
                        stopShimmer()
                    }
                    petRescueAnimalItemContainer.isVisible = true
                    Glide.with(root.context)
                        .load(item)
                        .fitCenter()
                        .into(petRescueAnimalItemImage)
                    petRescueAnimalItemType.text = "Cachorro"
                    petRescueAnimalItemBreed.text = breed
                }
            }

        }
    }

    fun submitData(newData: List<String>, newBreed: String,loading: Boolean){
        data.clear()
        data.addAll(newData)
        breed = newBreed
        isLoading = loading
        notifyDataSetChanged()
    }
}
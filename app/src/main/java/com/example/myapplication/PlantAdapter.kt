package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMain3Binding

class PlantAdapter : ListAdapter<Plant, PlantAdapter.PlantHolder> (ItemDiffUtil()){
    //var PlantList = listOf<Plant>()
    //set(value) {
    //    val callback = ListDiffUtil(PlantList, value)
    //    val result = DiffUtil.calculateDiff(callback)
    //    result.dispatchUpdatesTo(this)
    //    field = value
        //notifyDataSetChanged()
    //}
    var onPlantLongClickListener : ((Plant) -> Unit)? = null
    var onItemClickListener : ((Plant) -> Unit)? = null
    //lateinit var OnClickListener : OnPlantClickListener
    class PlantHolder(item: View) : RecyclerView.ViewHolder(item){
        val binding = ActivityMain3Binding.bind(item)
        fun bind (plant: Plant) = with(binding){
            im.setImageResource(plant.imageId)
            textView.setText(plant.title)
            textView2.setText(plant.desc)
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main3, parent, false)
        return PlantHolder(view)
    }

    override fun onBindViewHolder(holder: PlantHolder, position: Int) {
        val plant = getItem(position)
        holder.itemView.setOnLongClickListener {
            onPlantLongClickListener?.invoke(plant)
            true
        }

        holder.binding.cardView.setOnClickListener {
            onItemClickListener?.invoke(plant)
        }
        holder.bind(plant)
    }

   // override fun getItemCount(): Int {
    //    return PlantList.size
    //}
    //fun removePlant(plant: Plant) {
    //    PlantList.remove(plant)
    //    notifyDataSetChanged()
    //}
    //fun overridePlant(plant: Plant, index : Int){
    //    PlantList.removeAt(index)
    //    PlantList.add(plant)
    //}
    //fun addPlant(plant: Plant) {
    //    PlantList.add(plant)
    //    notifyDataSetChanged()
    //}

    //override fun OnPlantClick(plant: Plant, id: Int) {

    //}
}
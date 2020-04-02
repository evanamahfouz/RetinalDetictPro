package com.example.ratinadeticpro.Ui.ui.History

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ratinadeticpro.R
import com.example.ratinadeticpro.data.db.PredictImgEntity
import com.example.ratinadeticpro.databinding.ListHistoryBinding

class HistoryAdapter :
    ListAdapter<PredictImgEntity, HistoryAdapter.MyViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ListHistoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context)
            , R.layout.list_history, parent, false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.v("inBind", getItem(position).prediction)

        holder.bind(getItem(position))
    }


    class MyViewHolder(private val binding: ListHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: PredictImgEntity) {
            if (data.prediction == "DME") {
                binding.txtType.setBackgroundResource(R.drawable.circle_orange)

            } else if (data.prediction == "DRUSEN") {
                binding.txtType.setBackgroundResource(R.drawable.circle_yellow)


            } else if (data.prediction == "CNV") {
                binding.txtType.setBackgroundResource(R.drawable.circle_red)


            } else {
                binding.txtType.setBackgroundResource(R.drawable.circle_green)

            }
            binding.listModel = data


        }


    }

    class DiffCallback : DiffUtil.ItemCallback<PredictImgEntity>() {
        override fun areItemsTheSame(
            oldItem: PredictImgEntity,
            newItem: PredictImgEntity
        ): Boolean {

            // check if id is the same
            return oldItem.ID_patient == newItem.ID_patient
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: PredictImgEntity,
            newItem: PredictImgEntity
        ): Boolean {
            // check if content is the same
            // equals using data class
            return oldItem == newItem
        }
    }
}
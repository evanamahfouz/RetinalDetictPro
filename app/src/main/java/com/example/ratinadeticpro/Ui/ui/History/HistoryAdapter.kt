package com.example.ratinadeticpro.ui.ui.history

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ratinadeticpro.R
import com.example.ratinadeticpro.data.db.PredictImgEntity
import com.example.ratinadeticpro.databinding.ListHistoryBinding
import java.util.*
import kotlin.collections.ArrayList

class HistoryAdapter :
    ListAdapter<PredictImgEntity, HistoryAdapter.MyViewHolder>(DiffCallback()), Filterable {
    private var imageFilterList: List<PredictImgEntity>

    init {
        imageFilterList = currentList

    }

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
            when {
                data.prediction == "DME" -> binding.txtType.setBackgroundResource(R.drawable.circle_orange)
                data.prediction == "DRUSEN" -> binding.txtType.setBackgroundResource(R.drawable.circle_yellow)
                data.prediction == "CNV" -> binding.txtType.setBackgroundResource(R.drawable.circle_red)
                else -> binding.txtType.setBackgroundResource(R.drawable.circle_green)
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

    override fun getFilter(): Filter {
        Log.v("CurrentList", currentList.size.toString())
        imageFilterList = currentList
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charSearch = charSequence.toString()
                Log.v("CharSearch11!!", charSearch)
                Log.v("imageFilterSize!!", imageFilterList.size.toString())

                imageFilterList = if (charSearch.isEmpty()) {
                    Log.v("imageSizeEmptyChar", imageFilterList.size.toString())

                    currentList
                } else {
                    val resultList = ArrayList<PredictImgEntity>()
                    for (row in imageFilterList) {
                        if (row.eye_part.toLowerCase(Locale.ROOT).contains(
                                charSearch.toLowerCase(
                                    Locale.ROOT
                                )
                            ) || row.prediction.toLowerCase(Locale.ROOT).contains(
                                charSearch.toLowerCase(
                                    Locale.ROOT
                                )

                            ) || row.date.toLowerCase(Locale.ROOT).contains(
                                charSearch.toLowerCase(
                                    Locale.ROOT
                                )
                            ) || row.Probability.toLowerCase(Locale.ROOT).contains(
                                charSearch.toLowerCase(
                                    Locale.ROOT
                                )
                            )

                        ) {
                            resultList.add(row)
                        }

                    }
                    Log.v("ResultListSize", resultList.size.toString())
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = imageFilterList

                Log.v("InFilter", imageFilterList.size.toString())
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                imageFilterList = results.values as List<PredictImgEntity>
                submitList(imageFilterList)

                notifyDataSetChanged()
            }

        }
    }


}
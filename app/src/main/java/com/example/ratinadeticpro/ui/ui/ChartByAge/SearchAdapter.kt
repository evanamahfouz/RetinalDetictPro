package com.example.ratinadeticpro.ui.ui.ChartByAge


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
import com.example.ratinadeticpro.data.model.PredictImg
import com.example.ratinadeticpro.databinding.ListResearchBinding
import java.util.*
import kotlin.collections.ArrayList

class SearchAdapter :
    ListAdapter<PredictImg?, SearchAdapter.MyViewHolder>(DiffCallback()), Filterable {

    var imageFilterList = listOf<PredictImg?>()

    init {
        imageFilterList = currentList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ListResearchBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context)
            , R.layout.list_research, parent, false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.v("inBind", getItem(position)!!.prediction)

        holder.bind(getItem(position))
    }


    class MyViewHolder(private val binding: ListResearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: PredictImg?) {
            when {
                data!!.prediction == "DME" -> binding.txtType.setBackgroundResource(R.drawable.circle_orange)
                data.prediction == "DRUSEN" -> binding.txtType.setBackgroundResource(R.drawable.circle_yellow)
                data.prediction == "CNV" -> binding.txtType.setBackgroundResource(R.drawable.circle_red)
                else -> binding.txtType.setBackgroundResource(R.drawable.circle_green)
            }
            binding.listOfPrediction = data


        }


    }

    class DiffCallback : DiffUtil.ItemCallback<PredictImg?>() {


        override fun areItemsTheSame(
            oldItem: PredictImg,
            newItem: PredictImg
        ): Boolean {

            // check if id is the same
            return oldItem.id_patient == newItem.id_patient
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: PredictImg,
            newItem: PredictImg
        ): Boolean {
            // check if content is the same
            // equals using data class
            return oldItem == newItem
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charSearch = charSequence.toString()
                imageFilterList = if (charSearch.isEmpty()) {
                    imageFilterList
                } else {
                    val resultList = ArrayList<PredictImg?>()
                    for (row in imageFilterList) {
                        if (row!!.eye_part.toLowerCase(Locale.ROOT).contains(
                                charSearch.toLowerCase(
                                    Locale.ROOT
                                )
                            ) || row.gender.toLowerCase(Locale.ROOT).contains(
                                charSearch.toLowerCase(Locale.ROOT)
                            ) || row.prediction.toLowerCase(Locale.ROOT).contains(
                                charSearch.toLowerCase(
                                    Locale.ROOT
                                )
                            )
                        ) {
                            resultList.add(row)
                        }

                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = imageFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                imageFilterList = results?.values as List<PredictImg?>
                notifyDataSetChanged()
            }

        }
    }
}


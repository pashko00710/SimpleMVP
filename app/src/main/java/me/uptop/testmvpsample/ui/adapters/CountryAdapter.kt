package me.uptop.testmvpsample.ui.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import me.uptop.testmvpsample.R

class CountryAdapter(private var countries: MutableList<String>?) : RecyclerView.Adapter<CountryAdapter.ItemHolder>() {

    private val context: Context? = null
    private val currentTypeCode: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        return ItemHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        //        final CountryDto country = countries.get(position);
        //
        //        holder.titleCountry.setText(country.getName());
        //        holder.countryQuantity.setText(String.valueOf(country.getQuantityOfPeople()));
        //        holder.countryRating.setText(String.valueOf(country.getRating()));
    }

    override fun getItemCount(): Int {
        return countries!!.size
    }

    fun removeItem(position: Int) {
        countries!!.remove(countries!![position])
    }

    internal fun reloadAdapter(countries: MutableList<String>) {
        this.countries!!.clear()
        this.countries = countries
        notifyDataSetChanged()
    }

    fun editCountry(position: Int, country: String) {
        countries!![position] = country
    }

    fun addCountry(country: String) {
        countries!!.add(country)
    }

    fun getCountries(): List<String>? {
        return countries
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleCountry: TextView = itemView.findViewById(R.id.title_country)
        var countryQuantity: TextView = itemView.findViewById(R.id.country_quantity)
        var countryRating: TextView = itemView.findViewById(R.id.country_rating)
    }
}

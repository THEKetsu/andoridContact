package fr.isen.bender.andoridcontact

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import fr.isen.bender.andoridcontact.Model.Results
import com.squareup.picasso.Picasso

internal class  CAdapter(
    private var Results:ArrayList<Results>,
    val onItemClickListener: (Results) -> Unit) :
    RecyclerView.Adapter<CAdapter.MyViewHolder>(){

    internal class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val prenom: TextView = view.findViewById(R.id.prenom)
        val nom: TextView = view.findViewById(R.id.name)
        val mail: TextView = view.findViewById(R.id.mail2)
        val adresse: TextView = view.findViewById(R.id.adresse)
        val image: ImageView = view.findViewById(R.id.picture)
    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.cellule, parent, false)
            return MyViewHolder(view)
        }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val result=Results[position]
        holder.prenom.text = result.name?.first
        holder.nom.text=result.name?.last
        holder.mail.text=result.email
        holder.adresse.text= result.location?.street?.number.toString() + " " + result.location?.street?.name + " " + result.location?.state + " " + result.location?.city
        if (result.picture?.large!!.isNotEmpty()) {
            Picasso.get().load(result.picture?.large).into(holder.image)
        }
        holder.prenom.setOnClickListener {
            onItemClickListener(result)
        }
    }

    override fun getItemCount(): Int = Results.size

    fun refreshList(dishesFromAPI: ArrayList<Results>) {
            Results= dishesFromAPI
            println("Total Nombre d'élements :"+getItemCount())
            println("Tout les plats :"+Results)
            notifyDataSetChanged()

        }
}


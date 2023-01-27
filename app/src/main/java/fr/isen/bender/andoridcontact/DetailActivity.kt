package fr.isen.bender.andoridcontact

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import fr.isen.bender.andoridcontact.Model.Results
import fr.isen.bender.andoridcontact.databinding.ActivityDetail2Binding
import fr.isen.bender.andoridcontact.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetail2Binding
    private lateinit var personne: Results
    private lateinit var myCategoryAdapter : CAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetail2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar = supportActionBar
        setContentView(R.layout.activity_detail2)
        actionBar?.title = "AndroidContactDS"
        personne = intent.getSerializableExtra("detail") as Results
        if (personne.picture?.large!!.isNotEmpty()) {
            Picasso.get().load(personne.picture?.large).into(binding.PictureD)
        }
        println("Reçu :"+personne)
        binding.prenomnom.text =personne.name?.first +" "+ personne.name?.last
        binding.adresse.text = personne.location?.street?.number.toString() + " " + personne.location?.street?.name + " " + personne.location?.state + " " + personne.location?.city
        binding.Telephone.text= personne.phone
        binding.Register.text = personne.registered?.date
    }
}
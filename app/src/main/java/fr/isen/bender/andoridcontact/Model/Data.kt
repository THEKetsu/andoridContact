package fr.isen.bender.andoridcontact.Model

import com.google.gson.annotations.SerializedName


data class Data (

  @SerializedName("results" ) var results : ArrayList<Results> = arrayListOf(),
  @SerializedName("info"    ) var info    : Info?              = Info()

):java.io.Serializable
package com.example.zooapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import  kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*
import android.app.Activity
import android.content.Intent
import android.widget.AdapterView.OnItemClickListener



class MainActivity : AppCompatActivity() {

    var listOfAnimals = ArrayList<Animal>()
    var adapter:AnimalsAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //load  animals
        listOfAnimals.add(
            Animal("Baboon",
                "Baboons are primates comprising the genus Papio, one of the 23 genera of Old World monkeys. There are five species of baboons, commonly known as hamadryas baboon, Guinea baboon, olive baboon, yellow baboon and chacma baboon. Each species is native to one of five areas of Africa and the hamadryas baboon is also native to part of the Arabian Peninsula.[2] Baboons are among the largest non-hominoid primates and have existed for at least two million years",
                R.drawable.baboon,false))
        listOfAnimals.add(
            Animal("Bulldog",
                "The Bulldog, also known as the English Bulldog or British Bulldog, is a medium-sized dog breed. It is a muscular, hefty dog with a wrinkled face and a distinctive pushed-in nose",
                R.drawable.bulldog,false))
        listOfAnimals.add(
            Animal("Panda",
                "The giant panda ,known as the panda bear or simply the panda, is a bear native to South Central China.",
                R.drawable.panda,true))
        listOfAnimals.add(
            Animal("Swallow",
                "The swallows, martins, and saw-wings, or Hirundinidae, are a family of passerine birds found around the world on all continents, including occasionally in Antarctica. Highly adapted to aerial feeding, they have a distinctive appearance",
                R.drawable.swallow_bird,false))
        listOfAnimals.add(
            Animal("Tiger",
                "The tiger (Panthera tigris) is the largest living cat species and a member of the genus Panthera. It is most recognisable for its dark vertical stripes on orange-brown fur, with a lighter underside. It is an apex predator, primarily preying on ungulates such as deer and wild boar. ",
                R.drawable.white_tiger,true))
        listOfAnimals.add(
            Animal("Zebra",
                "Zebras  are African equines with distinctive black-and-white striped coats. There are three extant species: the Grévy's zebra , plains zebra, and the mountain zebra. Zebra stripes come in different patterns, unique to each individual.",
                R.drawable.zebra,false))

        adapter = AnimalsAdapter(this,listOfAnimals)
        tvListAnimal.adapter =  adapter


    }

//    fun delete(index:Int){
//        listOfAnimals.removeAt(index)
//        adapter!!.notifyDataSetChanged()
//    }
//
//    fun  add(index:Int){
//        listOfAnimals.add(index,listOfAnimals[index])
//        adapter!!.notifyDataSetChanged()
//    }

    inner class  AnimalsAdapter:BaseAdapter {
        var  listOfAnimals= ArrayList<Animal>()
        var context:Context?=null
        constructor(context:Context, listOfAnimals: ArrayList<Animal>):super(){
            this.listOfAnimals=listOfAnimals
            this.context=context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val animal =  listOfAnimals[p0]
            if( animal.isKiller ==true) {
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_killer_ticket, null)
                myView.tvDes.text = animal.des!!
                myView.tvName.text = animal.name!!
                myView.ivAnimalImage.setImageResource(animal.image!!)
                myView.ivAnimalImage.setOnClickListener {

                    val intent = Intent(context,AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des",animal.des!!)
                    intent.putExtra("image",animal.image!!)
                    context!!.startActivity(intent)


                }
                return myView

            }else {
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_ticket, null)
                myView.tvName.text = animal.name!!
                myView.tvDes.text = animal.des!!
                myView.ivAnimalImage.setImageResource(animal.image!!)
                myView.ivAnimalImage.setOnClickListener {


                    val intent = Intent(context,AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des",animal.des!!)
                    intent.putExtra("image",animal.image!!)
                    context!!.startActivity(intent)


                }
                return myView
            }
        }

        override fun getItem(p0: Int): Any {
            return listOfAnimals[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {

            return  listOfAnimals.size
        }

    }
}
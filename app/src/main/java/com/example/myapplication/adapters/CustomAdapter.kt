package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.MySpinnerLayoutBinding
import com.example.myapplication.dto.Empresas
import com.squareup.picasso.Picasso


class CustomAdapter(var lista : List<Empresas>) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {

        var inflater = LayoutInflater.from(parent.context)
        return CustomViewHolder(

            inflater.inflate(R.layout.my_spinner_layout,
                parent,
                false)
        )



    }

    override fun onBindViewHolder(
        holder: CustomViewHolder,
        position: Int
    ) {

        holder.render(lista[position])

    }


    override fun getItemCount() = lista.size



    class CustomViewHolder (view : View) : RecyclerView.ViewHolder (view){

        private var localBindin  : MySpinnerLayoutBinding = MySpinnerLayoutBinding.bind(view)
         fun render (item : Empresas) {

             localBindin.txtEmpresa.setText(item.name)
             Picasso.get()
                 .load(item.image)
                 .into(localBindin.imgEmpresa)


        }


    }



}
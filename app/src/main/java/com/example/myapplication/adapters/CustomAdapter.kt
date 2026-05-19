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


class CustomAdapter(
            var onClick : (Empresas) -> Unit,
            var onDelete : (Empresas) -> Unit )


    : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>(){

    var lista : MutableList<Empresas> = ArrayList<Empresas>()



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

        holder.render(lista[position], onClick, onDelete)

    }


    override fun getItemCount() = lista.size



    // solo cambiamos esto
    class CustomViewHolder (view : View) : RecyclerView.ViewHolder (view){

        private var localBindin  : MySpinnerLayoutBinding = MySpinnerLayoutBinding.bind(view)

        // aqui hacemos el evento onClick y decidimos lo que vamos hacer.
         fun render (item : Empresas,
                     onClick: (Empresas) -> Unit,
                     onDelete: (Empresas) -> Unit) {

             localBindin.txtEmpresa.setText(item.name)
             Picasso.get()
                 .load(item.image)
                 .into(localBindin.imgEmpresa)

            localBindin.imgEmpresa.setOnClickListener {
                onClick(item)
            }
            localBindin.txtEmpresa.setOnClickListener {
                onDelete(item)
            }


        }


    }



}
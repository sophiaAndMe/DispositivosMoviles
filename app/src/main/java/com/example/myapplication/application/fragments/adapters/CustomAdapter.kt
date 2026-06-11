package com.example.myapplication.application.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.MySpinnerLayoutBinding
import com.example.myapplication.dto.Empresas
import com.squareup.picasso.Picasso


class CustomAdapter(
            var onClick : (Empresas) -> Unit,
            var onDelete : (Empresas) -> Unit
) : ListAdapter<Empresas, CustomAdapter.CustomViewHolder>(EmpresaDiffCallback()){


    /*
    *ELIMIADO> var lista : MutableList<Empresas> = ArrayList<Empresas>()
    */

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {

        // esta inflando la vista que esta corriendo en ese momento
        val inflater = LayoutInflater.from(parent.context)

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

        holder.render(
            getItem(position),
            onClick,
            onDelete)

    }

    /*
    * ELIMINADO: override fun getItemCount(): Int = lista.size
    */


    /*
     * A NIVEL PRACTICO, SOLO DEBERIAMOS MODIFICAR ESTO
     * PARA LO QUE QUERAMOS
     */

    class CustomViewHolder (view : View) : RecyclerView.ViewHolder (view){
        private var localBindin  : MySpinnerLayoutBinding = MySpinnerLayoutBinding.bind(view)

        // aqui hacemos el evento onClick y decidimos lo que vamos hacer.
        fun render (item : Empresas,
                    onClick : (Empresas) -> Unit,
                    onDelete : (Empresas) -> Unit)
        {

             localBindin.txtEmpresa.text = item.name
             Picasso.get()
                 .load(item.image)
                 .into(localBindin.imgEmpresa)

            localBindin.imgEmpresa.setOnClickListener { onClick(item) }
            localBindin.txtEmpresa.setOnClickListener { onDelete(item) }

        }
    }

    // Implementacion del DiffUtil
    class EmpresaDiffCallback : DiffUtil.ItemCallback<Empresas>(){



        /*
        * Si los nombres son iguales, el sistema entiende que es la misma empresa
        * (mismo renglón de la lista) y pasa al siguiente metodo (onDelete).
        * Si los nombres son diferentes, el sistema asume que es una empresa totalmente nueva.
         */
        override fun areItemsTheSame(oldItem: Empresas, newItem: Empresas): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Empresas, newItem: Empresas): Boolean {
            return oldItem == newItem
        }
    }


}
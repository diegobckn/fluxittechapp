package com.diegobckn.fluxit.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.diegobckn.fluxit.R
import com.diegobckn.fluxit.api.RepoMain
import com.diegobckn.fluxit.api.Request
import com.diegobckn.fluxit.domain.Resource
import com.diegobckn.fluxit.domain.model.Pet
import com.diegobckn.fluxit.ui.vm.Factory
import com.diegobckn.fluxit.ui.vm.Main
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {
    private val viewModel by viewModels<Main>{
        Factory(RepoMain(Resource()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val petId: Long = activity?.intent?.extras?.get("petId") as Long

        viewModel.setPetId(petId)
        initObservers()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initObservers(){
        viewModel.fetchPet.observe(requireActivity(), Observer { result ->
            when (result) {
                is Request.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                }
                is Request.Success -> {
                    progress_bar.visibility = View.GONE
                    fillInfo(result.data)
                }
                is Request.Failure -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(requireContext(),"Error al traer datos: ${result.exception}",
                        Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun fillInfo(pet: Pet){
        text_view_petName.text = pet.name
        text_view_petTags.text = pet.getTagNames()
        //las imagenes no eran correctas para usarlas
    }
}
package com.diegobckn.fluxit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegobckn.fluxit.api.RepoMain
import com.diegobckn.fluxit.api.Request
import com.diegobckn.fluxit.domain.Resource
import com.diegobckn.fluxit.domain.model.Pet
import com.diegobckn.fluxit.ui.adapters.PetAdapter
import com.diegobckn.fluxit.ui.vm.Factory
import com.diegobckn.fluxit.ui.vm.Main
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), PetAdapter.OnPetClickListener {

    private val viewModel by viewModels<Main>{
        Factory(RepoMain(Resource()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Objects.requireNonNull(supportActionBar)?.setDisplayShowHomeEnabled(true);
        supportActionBar?.setIcon(R.mipmap.ic_launcher);
        supportActionBar?.title = "Listado de mascotas";

        initObservers()
        initRecycleView()
        initSwipeRefresh()
    }

    private fun initSwipeRefresh(){
        swipe_refresh_layout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun initObservers(){
        viewModel.fetchPetList.observe(this, Observer { result ->
            when (result) {
                is Request.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                }
                is Request.Success -> {
                    Toast.makeText(baseContext,"Éxito en la carga de datos",Toast.LENGTH_LONG).show()
                    progress_bar.visibility = View.GONE
                    recycle_view_pet_list.adapter =
                        PetAdapter(
                            this,
                            result.data,
                            this
                        )
                    swipe_refresh_layout.isRefreshing = false
                }
                is Request.Failure -> {
                    progress_bar.visibility = View.GONE
                    Toast.makeText(this,"Error en la carga de datos: ${result.exception}",
                        Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun initRecycleView(){
        recycle_view_pet_list.layoutManager = LinearLayoutManager(this)
        recycle_view_pet_list.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
    }

    override fun onPetClick(pet: Pet) {
        val detailActivity = Intent(this, DetailActivity::class.java)
        detailActivity.putExtra("petId", pet.id)
        detailActivity.putExtra("search", pet.name)
        startActivity(detailActivity)
    }

}
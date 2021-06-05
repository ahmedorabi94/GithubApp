package com.ahmedorabi.githubapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ahmedorabi.githubapp.R
import com.ahmedorabi.githubapp.data.api.Resource
import com.ahmedorabi.githubapp.databinding.FragmentHomeBinding
import com.ahmedorabi.githubapp.di.Injectable
import com.ahmedorabi.githubapp.ui.adapter.RepoAdapter
import com.ahmedorabi.githubapp.utils.EspressoIdlingResource
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : Fragment(), Injectable {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this

        initSearchBar()

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(HomeViewModel::class.java)


        viewModel.items.observe(viewLifecycleOwner, {
            it?.let { resource ->


                when (resource.status) {
                    Resource.Status.SUCCESS -> {

                        Timber.e("SUCCESS")
                        Timber.e("$resource.data")

                        binding.progressbar.visibility = View.GONE
                        binding.recyclerViewMain.visibility = View.VISIBLE


                        EspressoIdlingResource.decrement()
                        val adapter = RepoAdapter()
                        adapter.submitList(resource.data!!.items)
                        binding.recyclerViewMain.adapter = adapter


                    }
                    Resource.Status.ERROR -> {
                        binding.progressbar.visibility = View.GONE
                        binding.recyclerViewMain.visibility = View.VISIBLE
                        Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                    }
                    Resource.Status.LOADING -> {
                        binding.progressbar.visibility = View.VISIBLE
                        binding.recyclerViewMain.visibility = View.GONE
                    }
                }
            }
        })


        return binding.root
    }

    private fun initSearchBar() {
        binding.searchView.isActivated = true
        binding.searchView.onActionViewExpanded()
        binding.searchView.isIconified = false
        binding.searchView.clearFocus()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                EspressoIdlingResource.increment()

                viewModel.query.value = newText
                return false
            }
        })
    }

}
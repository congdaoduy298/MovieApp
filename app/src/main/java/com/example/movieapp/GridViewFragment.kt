package com.example.movieapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.databinding.GridViewMoviesBinding

class GridViewFragment : Fragment() {
    lateinit var binding: GridViewMoviesBinding
    lateinit var vm: HomeVM
    lateinit var adapter: GridMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GridViewMoviesBinding.inflate(inflater, container, false)
        vm = ViewModelProvider(this).get(HomeVM::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMovieList()
        registerMovieList()
        registerErrorList()
    }


    override fun onStart() {
        super.onStart()
        vm.getTopRatedMovie()
    }

    private fun setUpMovieList() {
        adapter = GridMovieAdapter()
        val n_cols = 2;
        val gm = GridLayoutManager(activity, n_cols)
        binding.restaurantGrid.layoutManager = gm
        binding.restaurantGrid.adapter = adapter
    }

    private fun registerMovieList() {
        vm.movieData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun registerErrorList() {
        vm.errEvent.observe(viewLifecycleOwner){
            //show dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.liner_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_linear -> {
                val controller = findNavController()
                controller.navigate(R.id.action_gridViewFragment_to_recycleViewFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
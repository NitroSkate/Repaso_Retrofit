package com.example.moviedex.database.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.moviedex.R
import com.example.moviedex.database.AppConstants
import com.example.moviedex.database.entities.Movie
import com.example.moviedex.database.fragments.MainContentFragment
import com.example.moviedex.database.fragments.MainListFragment
import com.example.moviedex.database.viewModel.MovieViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main_list.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity(), MainListFragment.SearchNewMovieListener {
    private lateinit var mainFragment : MainListFragment
    private lateinit var mainContentFragment: MainContentFragment
    private lateinit var movieViewModel: MovieViewModel

    private var movieList = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        //En teoria ya no usamos parcelable movieList = savedInstanceState?.getParcelableArrayList(AppConstants.dataset_saveinstance_key) ?: ArrayList()
        initMainFragment()
    }

    /*override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList(AppConstants.dataset_saveinstance_key, movieList)
        super.onSaveInstanceState(outState)
    }*/

    fun initMainFragment(){
        mainFragment = MainListFragment.newInstance(movieList)


        val resource = if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            R.id.main_fragment
        else {
            mainContentFragment = MainContentFragment.newInstance(Movie())
            changeFragment(R.id.land_main_cont_fragment, mainContentFragment)

            R.id.land_main_fragment
        }


        changeFragment(resource, mainFragment)
    }

    fun addMovieToList(movie: Movie) {
        movieList.add(movie)
        mainFragment.updateMoviesAdapter(movieList)
        Log.d("Number", movieList.size.toString())
        //e.e
    }

    override fun searchMovie(movieName: String) {
        //F
    }

    override fun managePortraitItemClick(movie: Movie) {
        val movieBundle = Bundle()
        movieBundle.putParcelable("MOVIE", movie)
        startActivity(Intent(this, MovieViewerActivity::class.java).putExtras(movieBundle))
    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }

    override fun manageLandscapeItemClick(movie: Movie) {
        mainContentFragment = MainContentFragment.newInstance(movie)
        changeFragment(R.id.land_main_cont_fragment, mainContentFragment)
    }

}
package co.prueba.peiky

import android.os.Bundle
import android.util.Log
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import co.prueba.peiky.viewModels.MainActivityViewModel
import co.prueba.peiky.adapter.NoteAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView

import androidx.appcompat.app.AppCompatActivity
import co.prueba.peiky.Utils.ApiConstans
import co.prueba.peiky.Utils.RecyclerViewHelper
import co.prueba.peiky.models.Result
import co.prueba.peiky.R.id.searchView




class MainActivity : AppCompatActivity() {


    private val mMainActivityViewModel by lazy {
        MainActivityViewModel.create(this)
    }
    private var page = 1
    private var offSetReal = 1
    private var lastPosition = 0
    val adapter = NoteAdapter()
    var category : String = "popular"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)


        // Now get the support action bar
        val actionBar = supportActionBar

        // Set toolbar title/app title
        actionBar!!.title = getString(R.string.title_bar)

        // Set action bar/toolbar sub title
        actionBar.subtitle = getString(R.string.subtitle_bar)


        recycler_view.setLayoutManager(LinearLayoutManager(this))
        recycler_view.setHasFixedSize(true)


        recycler_view.setAdapter(adapter)

        mMainActivityViewModel.init()
        mMainActivityViewModel.nicePlaces().observe(this, object : Observer<ArrayList<Result>> {
            override  fun onChanged(@Nullable nicePlaces: ArrayList<Result>) {
                //mAdapter.notifyDataSetChanged()
                adapter.setNotes(mMainActivityViewModel.nicePlaces().getValue()!!)
            }
        })

        recycler_view.addOnScrollListener(recyclerViewOnScrollListener);

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override  fun onQueryTextSubmit(query: String): Boolean {
                // Toast like print
                Log.v("busqueda",query)
                mMainActivityViewModel.search(query,ApiConstans.KEY_API,ApiConstans.LANGUAGE,offSetReal.toString())
                if (!searchView.isIconified) {
                    searchView.isIconified = true
                }
                return false
            }

            override  fun onQueryTextChange(s: String): Boolean {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {

            R.id.action_popular -> {
                page = 1
                offSetReal = 1
                category = "popular"
                mMainActivityViewModel.changeCategory(category,ApiConstans.KEY_API,ApiConstans.LANGUAGE,offSetReal.toString())
                return true
            }
            R.id.action_top_rated -> {
                page = 1
                offSetReal = 1
                category = "top_rated"
                mMainActivityViewModel.changeCategory(category,ApiConstans.KEY_API,ApiConstans.LANGUAGE,offSetReal.toString())
                return true
            }
            R.id.action_upcoming -> {
                page = 1
                offSetReal = 1
                category = "upcoming"
                mMainActivityViewModel.changeCategory(category,ApiConstans.KEY_API,ApiConstans.LANGUAGE,offSetReal.toString())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val mRecyclerViewHelper = RecyclerViewHelper.createHelper(recyclerView)
            val visibleItemCount = recyclerView!!.childCount
            val firstVisibleItem = mRecyclerViewHelper.findFirstVisibleItemPosition()
            if (mMainActivityViewModel.nicePlaces().getValue()!!.size != null && mMainActivityViewModel.nicePlaces().getValue()!!.isNotEmpty()) {
                var lastItem = firstVisibleItem + visibleItemCount

               // if (lastItem == page && page == mMainActivityViewModel.nicePlaces().getValue()!!.size) {
                if (lastItem ==  mMainActivityViewModel.nicePlaces().getValue()!!.size) {////
                    lastPosition = lastItem
                    val valuePage = page
                    offSetReal += 1
                    page += 1
                    Log.v("mMainActivityViewModel","Llamar mas")
                    mMainActivityViewModel.more(category,ApiConstans.KEY_API,ApiConstans.LANGUAGE,offSetReal.toString())
                    // llamar paginado
                }
            }
        }
    }
}

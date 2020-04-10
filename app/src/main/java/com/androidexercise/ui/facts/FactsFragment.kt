package com.androidexercise.ui.facts

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.androidexercise.R
import com.androidexercise.model.ResponseFacts
import com.androidexercise.util.NetworkUtil
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class FactsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var factsViewModel: FactsViewModel
    private lateinit var factsListView: ListView
    private lateinit var noFactsFoundTextView: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var factsAdapter: FactsAdapter
    private lateinit var mActiviy: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialiseViewModel()
        callFactsAPI()
        observeFactsCount()
    }

    private fun initialiseViewModel() {
        factsViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(FactsViewModel::class.java)
    }

    public fun callFactsAPI() {
        if (NetworkUtil.isNetworkAvailable(mActiviy)) {
            showProgress()
            factsViewModel.getFacts()
        } else {
            Toast.makeText(mActiviy, "Please connect to Internet.", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun observeFactsCount() {
        factsViewModel.factsObserver().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                populateListView(it)
            } else {
                hideProgress()
            }
        })
    }

    private fun populateListView(facts: ResponseFacts?) {
        if (facts?.rows!!.isNotEmpty()) {
            (mActiviy as FactsActivity).updateTitle(facts.title)
            noFactsFoundTextView.visibility = View.GONE
            factsListView.visibility = View.VISIBLE
            hideProgress()
            factsAdapter = FactsAdapter(
                mActiviy,
                facts.rows
            )
            factsListView.adapter = factsAdapter
        } else {
            hideProgress()
            noFactsFoundTextView.visibility = View.VISIBLE
            factsListView.visibility = View.GONE
        }
    }

    private fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_facts, container, false)

        factsListView = view.findViewById(R.id.factsListView)
        noFactsFoundTextView = view.findViewById(R.id.noFactsFoundTextView)
        progressBar = view.findViewById(R.id.progressBar)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            mActiviy = context
        }
    }

    companion object {
        fun newInstance(): FactsFragment {
            return FactsFragment()
        }
    }
}

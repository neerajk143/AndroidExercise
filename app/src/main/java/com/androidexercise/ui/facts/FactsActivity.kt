package com.androidexercise.ui.facts

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import com.androidexercise.R
import com.androidexercise.ui.BaseActivity


class FactsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facts)

        loadFragment(FactsFragment.newInstance())
    }

    private fun loadFragment(fragment: FactsFragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.factsFrameLayout, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    public fun updateTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_refresh) {
            var fragment =
                supportFragmentManager.findFragmentById(R.id.factsFrameLayout) as FactsFragment
            fragment.callFactsAPI()
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBackPressed() {
        finishAffinity()
    }


}

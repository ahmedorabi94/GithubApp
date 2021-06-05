package com.ahmedorabi.githubapp.ui

import android.widget.AutoCompleteTextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ahmedorabi.githubapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class HomeFragmentTest {


    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @get: Rule
    val espressoIdlingResoureRule = EspressoIdlingResourceRule()


    @Test
    fun isHomeFragmentDis() {
        onView(withId(R.id.homeFragmentId)).check(matches(isDisplayed()))

    }


    @Test
    fun test_RecyclerViewVisible() {

        onView(withId(R.id.searchView)).perform(click())

        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(typeText("movies"))

        onView(withId(R.id.recycler_view_main)).check(matches(isDisplayed()))

    }


}
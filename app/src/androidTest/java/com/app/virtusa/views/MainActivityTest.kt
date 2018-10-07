package com.app.virtusa.views

import android.support.annotation.NonNull
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule

import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click

import android.support.test.espresso.contrib.RecyclerViewActions
import com.app.virtusa.R
import com.app.virtusa.utils.EspressoIdlingResource
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.assertion.ViewAssertions.matches
import org.junit.Before
import org.junit.After
import android.support.v7.widget.RecyclerView
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    private val ITEM_POSITION = 40 // this is item index at rv  adapter

    private val TITLE_AT_THE_ITEM_POSITION = "ea voluptates maiores eos accusantium officiis tempore mollitia consequatur"


    @get:Rule
    var mActivityRule = ActivityTestRule(MainActivity::class.java)


    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)

    }


    @Test
    fun scrollToItemBelowFold_checkItIsText() {

        // for preventing perform exception we add delay because recyclerView visibility takes a little bit time on UI databinding
        Thread.sleep(500)

        // scroll to the position and then click on it
        onView(withId(R.id.rv_albums)).perform(RecyclerViewActions.actionOnItemAtPosition<AlbumAdapter.AlbumViewHolder>(ITEM_POSITION, click()))

        // lets verify  if text on clicked item is matching data from repository
        onView(withId(R.id.rv_albums)).check(matches(atPosition(ITEM_POSITION, hasDescendant(withText(TITLE_AT_THE_ITEM_POSITION)))))


    }


    // Unregister your Idling Resource so it can be garbage collected and does not leak any memory
    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }


    // for RecyclerView we create a custom matcher

    fun atPosition(position: Int, @NonNull itemMatcher: Matcher<View>): Matcher<View> {
        checkNotNull(itemMatcher)
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position) ?:
                // has no item on such position
                return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }


}
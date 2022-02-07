package com.example.week6exercise

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import kotlin.random.Random


@RunWith(AndroidJUnit4::class)
class ListActivityTest {

    @Test
    fun alertDialogTest() {

        launch(ListActivity::class.java)
        onView((withId(R.id.recyclerView))).perform(RecyclerViewActions.actionOnItemAtPosition
        <RecyclerView.ViewHolder>(2, clickOnViewChild(R.id.userProfilePic)))
        onView(withText("Profile")).check(matches(isDisplayed()))
        onView(withText("View"))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
            .perform(click())

    }

    @Test
    fun intentTest() {

        launch(MainActivity::class.java)
        val resultData = Intent()
        val user = User("Name 1",
            "Description ${Random.nextInt(100000000, 2147483647)}",
            0 , false)
        resultData.putExtra("extra_object", user)
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)

    }

    @Test
    fun testRecyclerListVisible() {
        launch(ListActivity::class.java)
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun testRecyclerListContent() {
        launch(ListActivity::class.java)
        for (i in 0..19) {
            onView(withId(R.id.recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(i, clickOnViewChild(R.id.userName)))
        }
    }

    @Test
    fun testRecyclerChild() {
        launch(ListActivity::class.java)
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, clickOnViewChild(R.id.userProfilePic))
        )
        onView(withText("View"))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
            .perform(click())
        onView(withId(R.id.btnFollow))
            .perform(click())
            .check(matches(isDisplayed()))
    }

    fun clickOnViewChild(viewId: Int) = object : ViewAction {

        override fun getConstraints() = null

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: UiController, view: View) = click().perform(uiController, view.findViewById<View>(viewId))
    }

}
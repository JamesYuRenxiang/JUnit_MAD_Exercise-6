package com.example.week6exercise

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SystemTest {

    @Test
    fun SystemTest() {

        ActivityScenario.launch(ListActivity::class.java)

        Espresso.onView((ViewMatchers.withId(R.id.recyclerView))).perform(RecyclerViewActions.actionOnItemAtPosition
        <RecyclerView.ViewHolder>(2, clickOnViewChild(R.id.userProfilePic)))

        Espresso.onView(ViewMatchers.withText("View"))
            .inRoot(RootMatchers.isDialog()) // <---
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())

        val user = User("MAD 0","Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua", 0, false)

        Espresso.onView(ViewMatchers.withId(R.id.txtUserName))
            .check { view: View, noViewFoundException: NoMatchingViewException? ->
            val name = (view as TextView).text.toString()
            Assert.assertNotEquals(user.Name, name)
        }

        Espresso.onView(ViewMatchers.withId(R.id.txtUserDescription))
            .check { view: View, noViewFoundException: NoMatchingViewException? ->
            val description = (view as TextView).text.toString()
            Assert.assertNotEquals(user.Description, description)
        }

        Espresso.onView(ViewMatchers.withId(R.id.btnFollow)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.btnFollow))
            .check(ViewAssertions.matches(ViewMatchers.withText("Unfollow")))

        Espresso.onView(ViewMatchers.withId(R.id.btnFollow)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.btnFollow))
            .check(ViewAssertions.matches(ViewMatchers.withText("Follow")))

        Espresso.onView(ViewMatchers.withId(R.id.btnFollow)).perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.btnFollow))
            .check(ViewAssertions.matches(ViewMatchers.withText("Unfollow")))

    }

    fun clickOnViewChild(viewId: Int) = object : ViewAction {

        override fun getConstraints() = null

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: UiController, view: View) = ViewActions.click()
            .perform(uiController, view.findViewById<View>(viewId))
    }
}
package com.example.week6exercise

import android.view.View
import android.widget.TextView
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun displayName() {

        launch(MainActivity::class.java)

        val user = User("MAD 1","Description 1", 0, false)

        onView(withId(R.id.txtUserName)).check { view: View, noViewFoundException: NoMatchingViewException? ->
            val name = (view as TextView).text.toString()
            Assert.assertNotEquals(user.Name, name)
        }

    }

    @Test
    fun displayDescription() {

        launch(MainActivity::class.java)

        val user = User("MAD 1","Description 1", 0, false)

        onView(withId(R.id.txtUserDescription)).check { view: View, noViewFoundException: NoMatchingViewException? ->
            val description = (view as TextView).text.toString()
            Assert.assertNotEquals(user.Description, description)
        }

    }

    @Test
    fun followTest() {

        launch(MainActivity::class.java)
        onView(withId(R.id.btnFollow)).perform(click())
        onView(withText("Followed"))
        //.inRoot(ToastMatcher())
        //.check(matches(isDisplayed()))
        onView(withId(R.id.btnFollow)).check(matches(withText("Unfollow")))
        onView(withId(R.id.btnFollow)).perform(click())
        onView(withId(R.id.btnFollow)).check(matches(withText("Follow")))
        onView(withId(R.id.btnFollow)).perform(click())
        onView(withId(R.id.btnFollow)).check(matches(withText("Unfollow")))


    }

}
package com.example.week6exercise

import android.content.Context
import android.util.Log
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class MyDBHandlerTest {

    private var appContext: Context? = null
    private var dbHandler: MyDBHandler? = null
    private var users: List<User>? = null

    @Before
    fun createDBHandler() {

        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        dbHandler = MyDBHandler(appContext!!)
        users = dbHandler!!.getUsers()
        Assert.assertNotNull(users)

    }

    @Test
    fun users_20UsersPresent()
    {
        Assert.assertEquals(20, users?.size)
        for (user in users!!) {
            Assert.assertNotNull(user)
            Assert.assertNotNull(user!!.ID)
            Assert.assertNotNull(user.Name)
            Assert.assertNotNull(user.Description)
            Assert.assertNotNull(user.Followed)
        }
    }

    @Test
    fun updateUser_ChangeFollowState() {
        val oldUser = users!![0]
        oldUser!!.Followed = !oldUser.Followed
        dbHandler!!.updateUser(oldUser)
        users = dbHandler!!.getUsers()
        Assert.assertNotNull(users)
        val newUser = users!!.stream()
            .filter { u: User? -> u!!.ID === oldUser.ID }
            .findFirst()
            .orElse(null)
        Assert.assertNotNull(newUser)
        assertEquals(oldUser.ID, newUser!!.ID)
        assertEquals(oldUser.Name, newUser.Name)
        assertEquals(oldUser.Description, newUser.Description)
        assertEquals(oldUser.Followed, newUser.Followed)
    }
}
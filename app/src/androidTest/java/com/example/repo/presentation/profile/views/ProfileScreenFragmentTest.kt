package com.example.repo.presentation.profile.views

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.repo.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class ProfileScreenFragmentTest {

    private lateinit var scenario: FragmentScenario<ProfileScreenFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_Repo)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testProfile_showChangeImageDialog() {
        onView(withId(R.id.imageView)).perform(click())
        onView(withId(R.id.chosePhotoItem)).check(matches(isDisplayed()))
    }

    @Test
    fun testProfile_setScreenData() {
        onView(withId(R.id.birthDateLabel)).check(matches(withText("Дата рождения")))
    }
}
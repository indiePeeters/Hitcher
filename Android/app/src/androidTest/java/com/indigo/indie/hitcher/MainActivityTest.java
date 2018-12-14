package com.indigo.indie.hitcher;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
/**
 * Created by Indie on 12/14/2018.
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private String from = "Eindhoven";
    private String to = "Amsterdam";

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void testHikeplannerInputScenario()
    {
        // input some text in the edit text
        Espresso.onView(withId(R.id.txtFrom)).perform(typeText(from));
        Espresso.onView(withId(R.id.txtTo)).perform(typeText(to));
        // close soft keyboard
        Espresso.closeSoftKeyboard();
        // perform button click
        Espresso.onView(withId(R.id.btn_calculateRoute)).perform(click());
        // checking the text in the textview
        Espresso.onView(withId(R.id.txtDestination)).check(matches(withText(to)));
    }

    @After
    public void tearDown() throws Exception {
    }

}
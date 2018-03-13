package com.acme.a3csci3130;
import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.StringStartsWith.startsWith;

/**
 * Created by Karthick P on 3/12/2018.
 */
@RunWith(AndroidJUnit4.class)

public class Update_erase_detailstest {
    @Rule public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    String name1 = "test1";
    String name2 = "testupdate";
    String email = "test1@gmail.com";
    String number = "9879";
    String address = "No:5, South Park Street, Halifax";
    String business = "Fisher";
    String province = "NS";

    @Test public void test(){
        createtest();
        updatetest();
        deletetest();
    }

    public void createtest(){

        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(typeText(name1),closeSoftKeyboard());
        onView(withId(R.id.email)).perform(typeText(email),closeSoftKeyboard());
        onView(withId(R.id.number)).perform(typeText(number),closeSoftKeyboard());
        onView(withId(R.id.business)).perform(typeText(business),closeSoftKeyboard());
        onView(withId(R.id.address)).perform(typeText(address),closeSoftKeyboard());
        onView(withId(R.id.province)).perform(typeText(province),closeSoftKeyboard());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(withText(name1)));
    }
    public void readtest(){
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.name)).check(matches(withText(name1)));
        onView(withId(R.id.email)).check(matches(withText(email)));
        onView(withId(R.id.number)).check(matches(withText(number)));
        onView(withId(R.id.address)).check(matches(withText(address)));
        onView(withId(R.id.business)).check(matches(withText(business)));
        onView(withId(R.id.province)).check(matches(withText(province)));
    }

    public void updatetest() {
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.name)).perform(replaceText(name2), closeSoftKeyboard());
        onView(withId(R.id.updateButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(withText(name2)));
    }
    public void deletetest() {
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());
        onView(withId(R.id.listView)).check(matches(not(isDisplayed())));
    }
}

package com.acme.a3csci3130;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.not;

/**
 * Created by Karthick P on 3/12/2018.
 * CRUDtest.java tests the basic CRUD operation for this application
 */
@RunWith(AndroidJUnit4.class)

public class CRUDtest {
    @Rule public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    String name1 = "test1";
    String name2 = "testupdate";
    String number = "9879";
    String address = "No:5, South Park Street, Halifax";
    String business = "Fisher";
    String province = "NS";
    /** Test() functions implements the test case in order 1. createtest 2. read test 3. updatetest 4.delete test
     * {@link CRUDtest#createtest()}-> creates the new contact with name as test 1
     * {@link CRUDtest#readtest()} -> read the test 1 contact and verifies with the entered data
     * {@link CRUDtest#updatetest()} ()} -> updates the "test1" contact name as "testupdate" and verifies it*
     * {@link CRUDtest#deletetest()}()} -> deletes the "testupdate" contact and verifies whether it is deleted.*/

    @Test public void test(){
        // testcase pre-condition: delete all contacts in firebase database.
        createtest();
        readtest();
        updatetest();
        deletetest();
    }

    public void createtest(){

        onView(withId(R.id.submitButton)).perform(click()); // -> clicks on Create Contact button in MainActivity
        onView(withId(R.id.name)).perform(typeText(name1),closeSoftKeyboard()); // types the data in respective fields
        onView(withId(R.id.number)).perform(typeText(number),closeSoftKeyboard());
        onView(withId(R.id.business)).perform(typeText(business),closeSoftKeyboard());
        onView(withId(R.id.address)).perform(typeText(address),closeSoftKeyboard());
        onView(withId(R.id.province)).perform(typeText(province),closeSoftKeyboard());
        onView(withId(R.id.submitButton)).perform(click());// -> clicks on Create Contact button in CreateContactActivity
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(withText(name1)));

    }
    public void readtest(){
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());// Clicks on the first contact created in the listview in MainActivity
        onView(withId(R.id.name)).check(matches(withText(name1))); // below test step verifies the read text from firebase
        onView(withId(R.id.number)).check(matches(withText(number)));
        onView(withId(R.id.address)).check(matches(withText(address)));
        onView(withId(R.id.business)).check(matches(withText(business)));
        onView(withId(R.id.province)).check(matches(withText(province)));
        pressBack();

    }

    public void updatetest() {
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.name)).perform(replaceText(name2), closeSoftKeyboard());// updates the name field in the CreateContactActivity.
        onView(withId(R.id.updateButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(withText(name2)));//verifies the update by tapping the listview first contact name in MainActivity
    }
    public void deletetest() {
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.deleteButton)).perform(click()); // Clicks on the delete button
        onView(withId(R.id.listView)).check(matches(not(isDisplayed())));// verifies whether delete is proper
    }
}

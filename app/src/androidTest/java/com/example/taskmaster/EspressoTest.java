package com.example.taskmaster;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testAddTaskCheck() {

        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.button3)).check(matches(withText("ADD TASK")));
        onView(isRoot()).perform(ViewActions.pressBack());
    }

    @Test
    public void testAllTaskCheck() {

        onView(withId(R.id.button2)).perform(click());
        onView(withId(R.id.textView6)).check(matches(withText("All Tasks")));
        onView(isRoot()).perform(ViewActions.pressBack());
    }

    @Test
    public void assertTextChanged() {

        onView(withId(R.id.button4)).perform(click());
        onView(withId(R.id.editTextTextPersonName3)).perform(typeText("Mohammad"), closeSoftKeyboard());
        onView(withId(R.id.button8)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());
        onView(withId(R.id.textView)).check(matches(withText("Mohammad's Tasks")));
    }

    @Test
    public void addNewTask() {

        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.taskTitleInput)).perform(typeText("Toyota"), closeSoftKeyboard());
        onView(withId(R.id.taskBodyInput)).perform(typeText("Japanese Company Cars"), closeSoftKeyboard());
        onView(withId(R.id.taskStateInput)).perform(typeText("complete"), closeSoftKeyboard());
        onView(withId(R.id.button3)).perform(click());

    }
    
}

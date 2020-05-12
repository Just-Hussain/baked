package com.example.baked;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.baked.ui.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(JUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    // From the android device, turn off the ui animations.
    @Test
    public void clickRecipe_CheckDetailActivity() {
        // waits for main data to load
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // from the master list to the recipe detail activity
        onView(withIndex(withId(R.id.tv_recipe_name), 0)).perform(click());

        onView(withIndex(withId(R.id.tv_ingredient_name), 0)).check(matches(withText("Graham Cracker crumbs")));

        // from the steps list to the step detail activity
        onView(withIndex(withId(R.id.tv_step_short_desc), 0)).perform(click());

        onView(withIndex(withId(R.id.tv_step_desc), 0)).check(matches(withText("Recipe Introduction")));
    }


    /**
     * By: FrostRocket
     * On: https://stackoverflow.com/questions/29378552/in-espresso-how-to-avoid-ambiguousviewmatcherexception-when-multiple-views-matc
     */
    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }
}

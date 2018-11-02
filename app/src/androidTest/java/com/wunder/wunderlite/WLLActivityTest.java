package com.wunder.wunderlite;

import com.wunder.R;
import com.wunder.WLLauncherActivity;
import com.wunder.WLListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.Espresso.onView;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class WLLActivityTest {

    @Rule
    public ActivityTestRule<WLListActivity> mActivityRule = new ActivityTestRule<>(WLListActivity.class);

    @Test
    public void listGoesOverTheFold() {
        onView(withId(R.id.fab_maps)).check(matches(isDisplayed()));
    }
}

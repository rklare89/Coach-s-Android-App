package project.reserver.capstone_robertklare;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.reserver.capstone_robertklare.R;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void viewAddTeam() {
        onView(ViewMatchers.withId(R.id.addTeamBtn))
                .perform(click());

        onView(withId(R.id.textView5)).check(matches(withText("Add Your New Team:")));
    }



    @Test
    public void viewPickupList() {
        onView(withId(R.id.pickupBtn))
                .perform(click());

        onView(withId(R.id.pickupTitle)).check(matches(withText("Pickup Players:")));
    }

    @Test
    public void addNewTeam() {
        onView(withId(R.id.addTeamBtn))
                .perform(click());

        onView(withId(R.id.insertTeamName))
                .perform(typeText("Test Team"));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addTeamSaveBtn))
                .perform(click());

        onView(withId(R.id.textView)).check(matches(withText("Coaching Companion:")));
    }
    @Test
    public void addThenSearch() {
        onView(withId(R.id.addTeamBtn))
                .perform(click());

        onView(withId(R.id.insertTeamName))
                .perform(typeText("Test Team"));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addTeamSaveBtn))
                .perform(click());

        onView(withId(R.id.searchMain))
                .perform(click());

        onView(withId(R.id.searchDoneButton))
                .perform(click());

        onView(withId(R.id.textView)).check(matches(withText("Coaching Companion:")));

    }

    @Test
    public void addPlayer() {

        onView(withId(R.id.addTeamBtn))
                .perform(click());

        onView(withId(R.id.insertTeamName))
                .perform(typeText("Test Team"));

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.addTeamSaveBtn))
                .perform(click());

        //R.id.teamList

        onView(withId(R.id.teamList))
                .check(matches(atPosition(0, hasDescendant(withText("Test Team")))));
    }

    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        // Positional assertion for RecyclerView
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public boolean matchesSafely(RecyclerView recyclerView) {
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }
        };

    }

}

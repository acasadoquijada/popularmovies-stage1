package com.example.popularmoviesstage1;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.navigation.Navigation;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.popularmoviesstage1.ui.MovieListFragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    private TestNavHostController navController;

    @Rule
    public final ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup(){
        // Setup navController
        navController =
                new TestNavHostController(ApplicationProvider.getApplicationContext());

        navController.setGraph(R.navigation.nav_graph);

        // Create a graphical FragmentScenario for the MovieListFragment
        FragmentScenario<MovieListFragment> listFragmentFragmentScenario = FragmentScenario.launchInContainer(MovieListFragment.class);

        // Set the NavController property on the fragment
        listFragmentFragmentScenario.onFragment(fragment -> Navigation.setViewNavController(fragment.requireView(), navController));
    }

    private void navigateToDetailFragment(){
        onView(withId(R.id.movieListRecyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void movieListIsDisplayed(){
        onView(withId(R.id.movieListRecyclerView)).check(matches(isDisplayed()));
    }

    @Test
    public void movieListFragmentNavigatesToMovieDetailFragment(){

        // Verify that performing a click changes the NavController’s state
        navigateToDetailFragment();

        assertEquals(navController.getCurrentDestination().getId(), R.id.movieDetailFragment);
    }
}

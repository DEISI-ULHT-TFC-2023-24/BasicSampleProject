package com.example.android.testing.espresso.BasicSample;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static org.hamcrest.Matchers.allOf;

import android.content.Intent;
import android.net.Uri;

public class BatteryYoutubeDrainTest {

    private static final String YOUTUBE_VIDEO_ID = "dQw4w9WgXcQ";

    @Rule
    public ActivityScenarioRule<BatteryDrainTestActivity> activityScenarioRule =
            new ActivityScenarioRule<>(BatteryDrainTestActivity.class);

    @Before
    public void setUp() {
        // Initialize Intents for testing Intent interactions
        Intents.init();
    }

    @After
    public void tearDown() {
        // Release Intents after each test
        Intents.release();
    }

    @Test
    public void testActivityOpensYouTubeApp() {
        intended(
                allOf(
                        hasAction(Intent.ACTION_VIEW),
                        hasData(Uri.parse("vnd.youtube:" + YOUTUBE_VIDEO_ID)),
                        toPackage("com.google.android.youtube")
                )
        );

        try {
            Thread.sleep(BatteryDrainTestActivity.WATCH_DURATION);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
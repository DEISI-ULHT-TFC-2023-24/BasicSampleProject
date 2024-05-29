package com.example.android.testing.espresso.BasicSample;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


public class BatteryYoutubeDrainTest {

    private static final String YOUTUBE_VIDEO_ID = "dQw4w9WgXcQ";
    private static final String YOUTUBE_PACKAGE_NAME = "com.google.android.youtube";
    public static final long WATCH_DURATION = BatteryDrainTestActivity.WATCH_DURATION + 1000;
    private static final long DELAY_BETWEEN_TESTS = 5000; // 5 seconds

    @Rule
    public ActivityScenarioRule<BatteryDrainTestActivity> activityScenarioRule =
            new ActivityScenarioRule<>(BatteryDrainTestActivity.class);

    @Before
    public void setUp() throws InterruptedException {
        // Delay before each test
        Thread.sleep(DELAY_BETWEEN_TESTS);
        // Initialize Intents for testing Intent interactions
        Intents.init();
    }

    @After
    public void tearDown() throws InterruptedException {
        // Release Intents after each test
        Intents.release();
        Thread.sleep(DELAY_BETWEEN_TESTS);
    }

    @Test
    public void testActivityOpensYouTubeApp() throws InterruptedException {
       /* intended(
                allOf(
                        hasAction(Intent.ACTION_VIEW),
                        hasData(Uri.parse("vnd.youtube:" + YOUTUBE_VIDEO_ID))
                )
        ); */
        Thread.sleep(WATCH_DURATION);
    }
}
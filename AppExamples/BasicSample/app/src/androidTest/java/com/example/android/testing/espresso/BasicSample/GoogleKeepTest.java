package com.example.android.testing.espresso.BasicSample;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class GoogleKeepTest {

    @Rule
    public ActivityScenarioRule<GoogleKeepTestActivity> activityRule =
            new ActivityScenarioRule<>(GoogleKeepTestActivity.class);

    @Test
    public void testGoogleKeep() {

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
                //botao
        onView(withText("Nova nota")).perform(click());

        try {

            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("Titulo")).perform(typeText("Teste do Google Keep"), closeSoftKeyboard());

        try {

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("Nota")).perform(typeText("teste automatizado no Google Keep"), closeSoftKeyboard());

        Espresso.pressBack();
    }
}




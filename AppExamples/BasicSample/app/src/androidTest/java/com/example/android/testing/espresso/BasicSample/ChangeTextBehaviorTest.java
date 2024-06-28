/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.testing.espresso.BasicSample;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ChangeTextBehaviorTest {

    public static final String STRING_TO_BE_TYPED =
            "Esta é a primeira linha do texto.\n" +
                    "Esta é a segunda linha do texto.\n" +
                    "Esta é a terceira linha do texto.\n" +
                    "Esta é a quarta linha do texto.\n" +
                    "Esta é a quinta linha do texto.\n" +
                    "Esta é a sexta linha do texto.\n" +
                    "Esta é a sétima linha do texto.\n" +
                    "Esta é a oitava linha do texto.\n" +
                    "Esta é a nona linha do texto.\n" +
                    "Finalmente, esta é a décima linha.";

    /**
     * Use {@link ActivityScenarioRule} to create and launch the activity under test, and close it
     * after test completes. This is a replacement for {@link androidx.test.rule.ActivityTestRule}.
     */
    @Rule public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    private void typeTextSlowly(final String text, final int delay) {
        StringBuilder typedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            typedText.append(c);
            onView(withId(R.id.editTextUserInput)).perform(replaceText(typedText.toString()));
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        onView(withId(R.id.editTextUserInput)).perform(closeSoftKeyboard());
    }

    @Test
    public void changeText_sameActivity() {
        // Type text slowly and then press the button.
        typeTextSlowly(STRING_TO_BE_TYPED, 180000 / STRING_TO_BE_TYPED.length());
        onView(withId(R.id.changeTextBt)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.textToBeChanged)).check(matches(withText(STRING_TO_BE_TYPED)));
    }

    @Test
    public void changeText_newActivity() {
        // Type text slowly and then press the button.
        typeTextSlowly(STRING_TO_BE_TYPED, 180000 / STRING_TO_BE_TYPED.length());
        onView(withId(R.id.activityChangeTextBtn)).perform(click());

        // This view is in a different Activity, no need to tell Espresso.
        onView(withId(R.id.show_text_view)).check(matches(withText(STRING_TO_BE_TYPED)));
    }


}






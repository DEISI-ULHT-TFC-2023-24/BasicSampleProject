package com.example.android.testing.espresso.BasicSample;


import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FlashlightTest {

    @Rule
    public ActivityScenarioRule<FlashlightTestActivity> activityScenarioRule =
            new ActivityScenarioRule<>(FlashlightTestActivity.class);
    @Before
    public void intentsInit() {
        Intents.init();
    }
    @After
    public void intentsTeardown() {
        Intents.release();
    }

    @Test
    public void testFlashlightEnergyConsumption() {
        FlashlightTestActivity flashlightTestActivity = startFlashlightTestActivity();

        flashlightTestActivity.turnOnFlashlightForTest();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flashlightTestActivity.turnOffFlashlightForTest();


        // Finalizar a atividade
        flashlightTestActivity.finish();
    }

    @Test
    public void testFlashLightEnergyConsumptionDesligada() {

        FlashlightTestActivity flashlightTestActivity = startFlashlightTestActivity();

        flashlightTestActivity.turnOffFlashlightForTest();

        try {
            Thread.sleep(180000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flashlightTestActivity.finish();

    }


    private FlashlightTestActivity startFlashlightTestActivity() {
        final FlashlightTestActivity[] activity = new FlashlightTestActivity[1];
        activityScenarioRule.getScenario().onActivity(activityInstance -> activity[0] = activityInstance);
        return activity[0];
    }

}

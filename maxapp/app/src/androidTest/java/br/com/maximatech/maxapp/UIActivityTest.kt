package br.com.maximatech.maxapp

import android.widget.TextView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UIActivityTest {
    @Rule
    @JvmField
    val activityRole = ActivityTestRule(SplashActivity::class.java)

    @Test
    fun uiTests(){
        Thread.sleep(2000)
        onView(withId(R.id.cardCliente)).perform(click())

        onView(allOf(
            instanceOf(TextView::class.java),
            withParent(withId(R.id.appbar))
        )).check(matches(withText("Dados do cliente")))

        onView(withId(R.id.historicoMenu)).perform(click())

        onView(allOf(
            instanceOf(TextView::class.java),
            withParent(withId(R.id.appbar))
        )).check(matches(withText("Hist. pedidos")))

        onView(withId(R.id.alvaraMenu)).perform(click())

        onView(allOf(
            instanceOf(TextView::class.java),
            withParent(withId(R.id.appbar))
        )).check(matches(withText("Alvarás")))

        onView(withContentDescription(R.string.abc_action_bar_up_description))
            .perform(click())

        onView(
            allOf(
                instanceOf(TextView::class.java),
                withParent(withId(R.id.cardCliente))
            )
        ).check(matches(withText("Clientes")))
    }

}
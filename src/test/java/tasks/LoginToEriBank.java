package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;
import userinterface.HomePageElements;
import userinterface.LoginPageElements;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isCurrentlyVisible;

public class LoginToEriBank implements Task {
    private final String username;
    private final String password;

    @Step("Login with valid username 'username")
    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                SendKeys.of(this.username).into(LoginPageElements.USERNAME_FIELD),
                SendKeys.of(this.password).into(LoginPageElements.PASSWORD_FIELD),
                Click.on(LoginPageElements.LOGIN_BUTTON),
                WaitUntil.the(HomePageElements.BALANCE_CHECK,isCurrentlyVisible()).forNoMoreThan(15).seconds()
        );
    }

    public LoginToEriBank(String username, String password){
        this.username = username;
        this.password = password;
    }

    public static LoginToEriBank loginToEriBank(String username, String password) {
        return instrumented(LoginToEriBank.class
                ,username,password);
    }
}

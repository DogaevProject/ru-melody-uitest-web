package ru.melody.web.steps.LoginSteps;

import com.codeborne.selenide.Condition;
import ru.melody.web.elements.MainPageElements;
import ru.melody.web.elements.LoginPageElements;
import ru.melody.web.model.Administration.Users.Employee;
import ru.melody.web.steps.BaseSteps;

import static com.codeborne.selenide.Selenide.page;

/**
 *  Страница авторизации
 */
public class LoginSteps  extends BaseSteps {

    private LoginPageElements loginPageElementsMobile = page(LoginPageElements.class);
    private MainPageElements mainPageElements = page(MainPageElements.class);


    /**
     * Вводим Login пользователя
     * @param login input text login
     * @return
     */
    public LoginSteps setLoginField(String login) {
        loginPageElementsMobile.getLogin().setValue(login);
        return this;
    }

    /**
     * Вводим пароль пользователя
     * @param password input text password
     * @return
     */
    public LoginSteps setPasswordField(String password) {
        loginPageElementsMobile.getPassword().setValue(password);
        return this;
    }

    /**
     * Авторизация под пользователем
     * @return
     */
    public LoginSteps loginAs(Employee user) {
        loginPageElementsMobile.getLogin().waitUntil(Condition.visible, 20000);
        setLoginField(user.getLoginName());
        setPasswordField(user.getPassword());
        loginPageElementsMobile.getLogon().click();
        return this;
    }

    /**
     * Ожидание открытия главной страницы после авторизации
     */
    public void waitLoadMainPage() {
        // Ожидание появления маски загрузки
         waitMaskOfLoading();
        // Ожидание кнопки Главного Меню
        mainPageElements.getButtonMainMenu().waitUntil(Condition.visible, 30000);
    }


}

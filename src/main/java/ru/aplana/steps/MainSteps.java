package ru.aplana.steps;

import cucumber.api.java.ru.Когда;
import ru.aplana.pages.MainPage;

public class MainSteps {
    @Когда("загружена страница Вклады")
    public void loadPage() {
        MainPage mainPage = new MainPage();
        mainPage.clickOnElement(mainPage.contributionsBtn);
    }
}
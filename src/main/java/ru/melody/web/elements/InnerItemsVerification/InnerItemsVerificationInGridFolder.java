package ru.melody.web.elements.InnerItemsVerification;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public class InnerItemsVerificationInGridFolder extends InnerItemsVerification {

    @Override
    public ElementsCollection getElementsOfInnerItems() {
       return $$(By.xpath("//a[contains(@href,'/open')]//ancestor::table"));
    }
}

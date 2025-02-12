package helper;

import org.openqa.selenium.By;
import model.ElementInfo;

    public class ElementHelper
    {

        public static By getElementInfoToBy(ElementInfo elementInfo) {
            By by = null;
            if (elementInfo.getType().equals("className")) {
                by = By.className(elementInfo.getValue());
            } else if (elementInfo.getType().equals("id")) {
                by = By.id(elementInfo.getValue());
            }else if (elementInfo.getType().equals("xpath")) {
                by = By.xpath(elementInfo.getValue());
            }
            return by;
        }
    }


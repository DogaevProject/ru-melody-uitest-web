package ru.melody.data;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.testng.TextReport;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.text.NumberFormat;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static org.testng.Assert.fail;


public abstract class BaseTest {

    private static final String http = "http://";

    @BeforeClass
    public void setUpConfigurations() throws Exception {
        setDriverByName("chrome_mac");
        Configuration.baseUrl = http + "ryazangov-test.melody1.ru";
        Configuration.timeout = 8000;
        TextReport.onSucceededTest = true;
        TextReport.onFailedTest = true;
        Configuration.savePageSource = false;
        Configuration.fileDownload = Configuration.FileDownloadMode.PROXY;

        open("/");
    }

    /**
     * clear Cache browser
     */
    @AfterClass
    public void tearDown() {
        Configuration.remote = null;
        clearBrowserCache();
        close();
    }

    /**
     * Инициализация браузера
     *
     * @param browserType передаваемое имя браузера
     */
    private void setDriverByName(String browserType) throws Exception {
        final String resourcesFolder = ".//src/main/resources/drivers/";
        switch (browserType) {
            case "ie":
                System.setProperty("webdriver.ie.driver", resourcesFolder + "IEDriverServer.exe");
                Configuration.browser = "ie";
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", resourcesFolder + "chromedriver.exe");
                Configuration.browser = "chrome";
                break;
            case "chrome_linux":
                System.setProperty("webdriver.chrome.driver", resourcesFolder + "chromedriver_linux");
                Configuration.browser = "chrome";
                Configuration.browserSize = "1920x1080";
                break;
            case "chrome_mac":
                System.setProperty("webdriver.chrome.driver", resourcesFolder + "chromedriver_mac");
                Configuration.browser = "chrome";
                break;
            case "gecko":
                System.setProperty("webdriver.gecko.driver", resourcesFolder + "geckodriver.exe");
                Configuration.browser = "gecko";
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", resourcesFolder + "MicrosoftWebDriver.exe");
                Configuration.browser = "edge";
                break;
            default:
                throw new Exception("Неизвестный браузер / имя драйвера");
        }
    }

    /**
     * clear Cookie browser
     */
    public static void clearCookies() {
        WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        refresh();
        sleep(300);
    }

    // -------------------------------Automation Randomizing Data-----------------------------
    public static Random random = new Random();

    /**
     * Метод выбора случайного значение из множества перечислений
     * пример использования: - (randomEnum(Status.class)
     */
    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    /**
     * Случайное Логическое значение
     */
    public static boolean randomBoolean() {
        return Math.random() < 0.5;
    }

    /**
     * Метод генерирующий случайное Строковое значение - например, .SendKeys(randomString(10));
     */
    public static String randomString(int length) {
        final String data = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIKLMNOPQRSTVXYабвгдеёжзиклмнопрст" +
                "уфхцчшщъыьэюяАБВГДЕЁЖЗИКЛМНОПРСТУФХЦЧШЩъЪЫЬЭЮЯ";
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++)
            sb.append(data.charAt(random.nextInt(data.length())));

        return sb.toString();
    }

    /**
     * Метод генерирующий случайные Спецсимволы
     * например, .SendKeys(randomSpecialCharacters(10));
     */
    public static String randomSpecialCharacters(int length) {
        final String data = "!\"'#$%()*,-./`:;<=>&?@[\\]^_{|}~«»";

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(data.charAt(random.nextInt(data.length())));

        return sb.toString();
    }

    /**
     * Метод генерирующий случайное строковое значение (Заглавные латинские буквы)
     * Максимальное значение для полей: Строка, например,
     * SendKeys(randomString(10));
     *
     * @param length кол-во вводимых символов
     */
    public static String randomIdentifier(int length) {
        final String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(data.charAt(random.nextInt(data.length())));

        return sb.toString();
    }

    /**
     * Метод генерирующий случайный email
     * <p>
     * пример - .SendKeys(randomEmail());
     */
    public static String randomEmail() {
        int lengthName = 10;
        int lengthFirstDomain = 5;
        int lengthSecondDomain = 3;

        String name = "0123456789abcdefghijklmnopqrstuvwxyz";
        String firstDomain = "0123456789abcdefghijklmnopqrstuvwxyz";
        String secondDomain = "abcdefghijklmnopqrstuvwxyz";

        String email = new String();
        StringBuilder sb = new StringBuilder(lengthName);
        for (int i = 0; i < lengthName; i++)
            sb.append(name.charAt(random.nextInt(name.length())));
        email += sb;

        sb = new StringBuilder(lengthFirstDomain);
        for (int i = 0; i < lengthFirstDomain; i++)

            sb.append(firstDomain.charAt(random.nextInt(firstDomain.length())));
        email += "@" + sb;

        sb = new StringBuilder(lengthSecondDomain);
        for (int i = 0; i < lengthSecondDomain; i++)
            sb.append(secondDomain.charAt(random.nextInt(secondDomain.length())));
        email += "." + sb;

        return email;
    }

    /**
     * Метод получения псевдослучайного целого числа от min до max (включая max);
     *
     * @param maxNumber значение максимального диапазона числа
     * @return целое случайное число
     */
    public static String randomInt(int maxNumber) {
        int min = 1; // Минимальное число для диапазона
        maxNumber -= min;
        int i = (int) (Math.random() * ++maxNumber) + min;
        return Integer.toString(i);
    }

    /**
     * Метод создания строки содержащей случайую Дату и Время
     * <p>
     * пример, Дата - .SendKeys(String.valueOf(randomDateTime));
     */
    public static String randomDateTime() {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumIntegerDigits(2);

        int year = randIntBetween(2016, 2040);
        int monthi = randIntBetween(1, 12);
        int dayi = randIntBetween(1, 28);

        String day = formatter.format(dayi);
        String month = formatter.format(monthi);
        String hour = formatter.format(randIntBetween(0, 23));
        String minute = formatter.format(randIntBetween(0, 59));
        String second = formatter.format(randIntBetween(0, 59));

        return (day + "." + month + "." + year + " " + hour + ":"
                + minute + ":" + second);

    }

    /**
     * Метод создания строки содержащей случайный Телефон
     */
    public static String randomPhone() {
        NumberFormat minimum = NumberFormat.getNumberInstance();
        NumberFormat maximum = NumberFormat.getNumberInstance();

        minimum.setMinimumIntegerDigits(3);
        maximum.setMaximumIntegerDigits(2);
        int a = randIntBetween(800, 999);

        String b = minimum.format(randIntBetween(1, 999));
        String c = maximum.format(randIntBetween(1, 999));
        String d = maximum.format(randIntBetween(1, 999));

        return "8(" + a + ")" + b + "-" + c + "-" + d;
    }

    /**
     * Метод создания строки содержащей случайую Дату
     */
    public static String randomDate() {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumIntegerDigits(2);

        int year = randIntBetween(2016, 2040);
        int monthi = randIntBetween(1, 12);
        int dayi = randIntBetween(1, 28);

        String day = formatter.format(dayi);
        String month = formatter.format(monthi);

        return (day + "." + month + "." + year);
        // пример, Дата - .SendKeys(String.valueOf(randomDate));
    }

    /**
     * Метод создания ColorHEX содержащей случайный цвет. Может применяться для
     * выбора случайного цвета в записи справочника или таблицы
     */
    public static String randomColour() {
        return "#" + randomInt(9) + randomInt(9) + randomInt(9)
                + randomInt(9) + randomInt(9) + randomInt(9);
    }

    /**
     * Метод создания целого числа в диапазоне От и До
     */
    public static int randIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }
}

package pages;
import app.AppConfig;
import com.codeborne.selenide.Selenide;

public abstract class Page {

    public void open() {
        String url = AppConfig.baseUrl;
        Selenide.open(url);
    }
}

package tarea;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginSection;
import pages.MainPage;
import pages.MenuSection;
import pages.Settings;
import session.Session;

public class UpdateProfileTest {
    MenuSection menuSection = new MenuSection();
    MainPage mainPage = new MainPage();
    LoginSection loginSection = new LoginSection();
    Settings settingsLogin = new Settings();

    @BeforeEach
    public void open(){
        Session.getInstance().getBrowser().get("http://todo.ly/");

        mainPage.loginButton.click();
        loginSection.emailTextBox.setText("brendaaa@gmailm");
        loginSection.emailTextBox.clearSetText("brendaaa@gmail.com");
        loginSection.pwdTextBox.setText("12345");
        loginSection.loginButton.click();
    }
    @AfterEach
    public  void  close(){
        Session.getInstance().closeSession();
    }

    @Test
    public void updateName() throws InterruptedException {
        String newName = "Brendaaaaaa";
        mainPage.settingsButton.click();
        settingsLogin.fullName.clearSetText(newName);
        settingsLogin.okButton.click();

        Thread.sleep(1000);

        mainPage.settingsButton.click();
        String actualName = settingsLogin.fullName.getValue();
        Assertions.assertEquals("Brendaaaaaa", actualName,
                "Error No se actualizó el nombre");
    }
}

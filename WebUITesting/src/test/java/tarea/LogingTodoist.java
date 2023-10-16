package tarea;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.Homepage;
import pages.LoginSection2;
import session.Session;

public class LogingTodoist {
    LoginSection2 loginSection2 = new LoginSection2();
    Homepage homepage = new Homepage();

    @AfterEach
    public void close(){
        Session.getInstance().closeSession();
    }
    @BeforeEach
    public void open(){
        Session.getInstance().getBrowser().get("https://todoist.com/auth/login");
    }
    @Test
    public void loginTesting(){
        loginSection2.emailTextBox.setText("brendaaa@gmail.com");
        loginSection2.pwdTextBox.setText("szdrwgwsrdfgvws");
        loginSection2.loginButton.click();

        Assertions.assertTrue(homepage.todayTitle.isControlDisplayed(),
                "Error no me pude iniciar sesion");

    }

}

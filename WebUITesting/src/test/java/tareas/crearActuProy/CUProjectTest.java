package tareas.crearYActualizarProyecto;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByChained;

import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CUProjectTest {

    Random rand = new Random();
    ChromeDriver chrome;

    static String nameProject;

    @BeforeEach
    public void setUp() throws InterruptedException{

        System.setProperty("webdriver.chrome.driver","src/test/resources/chrome/chromedriver.exe");
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        chrome.get("http://todo.ly/");

        chrome.findElement(By.xpath("//img[@src=\"/Images/design/pagelogin.png\"]")).click();
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys("upbapi@upbapi.com");
        // set password
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys("12345");
        // click login
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();
        Thread.sleep(5000);
        // verificar si existe el control del logout

        Assertions.assertTrue((chrome.findElements(By.xpath("//a[text()='Logout']")).size() == 1),
                "ERROR no se pudo ingresar a la sesion");
    }

    @Test
    @Order(1)
    public void createProject() throws InterruptedException{
        nameProject="VivianMarino"+rand.nextInt(255);
        chrome.findElement(By.xpath("//td[text()='Add New Project']")).click();
        chrome.findElement(By.id("NewProjNameInput")).sendKeys(nameProject);
        chrome.findElement(By.id("NewProjNameButton")).click();
        Thread.sleep(5000);
        Assertions.assertTrue((chrome.findElement(By.id("mainProjectList")).findElements(By.xpath("//li//td[text()='"+nameProject+"']")).size() == 1),
                "ERROR no se pudo crear el proyecto");
        Thread.sleep(5000);
        // verificar si existe el control del logout


    }

    @Test
    @Order(2)
    public void updateProject() throws InterruptedException{
        chrome.findElement(By.id("mainProjectList")).findElement(By.xpath("//li//td[text()='"+nameProject+"']")).click();
        chrome.findElement(By.xpath("//div[@style='display: block;' and @class='ProjItemMenu']")).click();
        chrome.findElement(By.id("projectContextMenu")).findElement(By.xpath("//li[@class='edit']")).click();
        Thread.sleep(3000);
        chrome.findElement(By.id("mainProjectList")).findElement(By.xpath("//li//td//div[@id='ProjectEditDiv']")).findElement(By.xpath("//input[@id='ItemEditTextbox']")).sendKeys("UPDATE");
        chrome.findElement(By.xpath("//img[@id='ItemEditSubmit']")).click();
        Thread.sleep(3000);
        String updatedName = nameProject + "UPDATE";
        Assertions.assertTrue((chrome.findElement(By.id("mainProjectList")).findElements(By.xpath("//li//td[text()='"+updatedName+"']")).size() == 1),
                "ERROR no se pudo actualizar el proyecto");
        Thread.sleep(5000);
    }

    @AfterEach
    public void closeBrowser(){
        chrome.quit();
    }
}

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
 
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
 
public class SeleniumTest {
    
    private WebDriver driver;
    
    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
    }
        
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    //TESTE 01 verificarPaginaInicial()
    @Test
    public void test01() {
        driver.get("https://www.wikipedia.org/");
        String currentUrl = driver.getCurrentUrl();
        String expected = "https://www.wikipedia.org/";
        assertEquals(expected, currentUrl);
    }

    //TESTE 02 testarPesquisaFuncionalidade()
    @Test
    public void test02() {
        driver.get("https://www.wikipedia.org/");
        WebElement searchBox = driver.findElement(By.name("search"));
        searchBox.sendKeys("Selenium_(software)");
        searchBox.submit();
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("Selenium (software)"));
    }
    
	 //TESTE 03 testarEditarPagina()
    @Test
    public void test03() {
        driver.get("https://en.wikipedia.org/wiki/Selenium_(software)");
        WebElement editLink = driver.findElement(By.id("ca-edit"));
        editLink.click();
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("Edit"));
    }

    //TESTE 04 testarVisualizarHistorico()
    @Test
    public void test04() {
        driver.get("https://en.wikipedia.org/wiki/Selenium_(software)");
        WebElement viewHistoryLink = driver.findElement(By.id("ca-history"));
        viewHistoryLink.click();
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("Selenium (software): Revision history"));
    }

    //TESTE 05 testarLinksExternosEmArtigo()
    @Test
    public void test05() {
        driver.get("https://en.wikipedia.org/wiki/Selenium_(software)");
        WebElement externalLink = driver.findElement(By.xpath("//a[contains(@href, 'selenium.dev')]"));
        Actions actions = new Actions(driver);
        actions.click(externalLink).perform();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        String newWindowTitle = driver.getTitle();
        assertTrue(newWindowTitle.contains("Selenium"));
    }
    
    //TESTE 06 testarLinkParaGitHub()
    @Test
    public void test06() {
        driver.get("https://en.wikipedia.org/wiki/Selenium_(software)");
        // Verifica se há um link para o repositório do Selenium no GitHub
        WebElement gitHubLink = driver.findElement(By.cssSelector(".external.text"));
        assertNotNull(gitHubLink);
    }

    //TESTE07 testarTituloPagina()
    @Test
    public void test07() {
        driver.get("https://en.wikipedia.org/wiki/Selenium_(software)");
        String pageTitle = driver.getTitle();
        assertTrue(pageTitle.contains("Selenium (software)"));
    }

    //TESTE08  testarPresencaSecaoHistory()
    @Test
    public void test08() {
        driver.get("https://en.wikipedia.org/wiki/Selenium_(software)");
        WebElement historySection = driver.findElement(By.id("History"));
        assertNotNull(historySection);
    }

    //TESTE 09 testarLinkDoGitHub()
    @Test
    public void test09() {
        driver.get("https://en.wikipedia.org/wiki/Selenium_(software)");
        WebElement externalLink = driver.findElement(By.xpath("//a[contains(@href, 'github.com/SeleniumHQ/')]"));
        Actions actions = new Actions(driver);
        actions.click(externalLink).perform();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        String newWindowTitle = driver.getTitle();
        assertTrue(newWindowTitle.contains("Selenium"));
    }

    //TESTE 10 Clicar em uma coluna e verificar
    @Test
    public void test10() {
        driver.get("https://en.wikipedia.org/wiki/Selenium_(software)");
        WebElement seleniumIDELink = driver.findElement(By.linkText("Selenium IDE"));
        seleniumIDELink.click();
        WebElement pageTitle = driver.findElement(By.tagName("h3"));
        assertEquals("Selenium IDE[edit]", pageTitle.getText());
    }
    
}
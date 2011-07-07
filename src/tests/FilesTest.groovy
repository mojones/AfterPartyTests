package tests

import java.util.concurrent.TimeUnit
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.firefox.internal.ProfilesIni
import org.openqa.selenium.By

/**
 * Created by IntelliJ IDEA.
 * User: martin
 * Date: 07/07/11
 * Time: 10:26
 * To change this template use File | Settings | File Templates.
 */
class FilesTest extends AfterPartyTestCase{

     void setUp() {
        ProfilesIni allProfiles = new ProfilesIni()
        FirefoxProfile profile = allProfiles.getProfile('default')
        driver = new FirefoxDriver(profile)
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS)
//         driver = new FirefoxDriver()
    }

    void testCreateStudy(){
        driver.get(url + "/study/create")
        driver.findElement(By.xpath("//textarea[@id='name']")).sendKeys("Functional testing study")
        driver.findElement(By.xpath("//textarea[@id='description']")).sendKeys("A study automatically created for functional testing by webdriver")
        driver.findElement(By.xpath("//input[@id='create']")).click()
        assert driver.findElement(By.xpath("//body")).getText().contains('created')

    }

     void tearDown() {

    }

}

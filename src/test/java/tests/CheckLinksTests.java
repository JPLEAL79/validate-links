package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CheckLinksTests extends BaseTests {


    @Test
    public void validateLinks() {
        loginPage.Login("standard_user", "secret_sauce");


        List<WebElement> links = driver.findElements(By.tagName("a"));
        List<String> urls = links.stream()
                .map(link -> link.getAttribute("href"))
                .collect(Collectors.toList());

        AtomicInteger brokenCount = new AtomicInteger();
        AtomicInteger validCount = new AtomicInteger();

        urls.parallelStream().forEach(url -> {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode >= 400) {
                    System.out.println("Broken link: " + url + " - Response code: " + responseCode);
                    brokenCount.incrementAndGet();
                } else {
                    System.out.println("Valid link: " + url + " - Response code: " + responseCode);
                    validCount.incrementAndGet();
                }
            } catch (IOException e) {
                System.out.println("Error checking link: " + url + " - Exception: " + e.getMessage());
                brokenCount.incrementAndGet();
            }
        });

        System.out.println("Total valid links: " + validCount);
        System.out.println("Total broken links: " + brokenCount);
    }
}


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Scanner;


public class Main {
    public static void main(String[] args)  {

        WebDriver driver=fonsiyon.driver();

        try {

            while (true){
                Scanner cm=new Scanner(System.in);
                String a= cm.nextLine();
                fonsiyon.secim(a,driver);
            }

        } finally {
            System.out.println("sistem kapatılıyor...");

        }







    }



}


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class fonsiyon {



    static public WebDriver driver(){
        System.setProperty("webdriver.chrome.driver", "./src/main/java/Driver/chromedriver.exe");
        WebDriver driver =new ChromeDriver();

        return driver;
    }


    static public void login(WebDriver a){
        String url="https://accounts.binance.com/en/login?return_to=aHR0cHM6Ly93d3cuYmluYW5jZS5jb20vZW4%3D";
        a.get(url);
        WebElement username=a.findElement(new By.ByXPath("//input[@name='email']"));
        username.sendKeys("elif-enes99@hotmail.com");
        WebElement password= a.findElement(new By.ByXPath("//input[@name='password']"));
        password.sendKeys("Sonsuz_m1999");
        WebElement login_button= a.findElement(new By.ByXPath("//button[@id='click_login_submit']"));
        login_button.click();

    }


    static public double altrade(String alınacak, WebDriver a){
        String link=String.format("https://www.binance.com/tr/trade/%s?layout=pro",alınacak);
        a.get(link);
        String b= String.valueOf(Double.parseDouble(a.findElement(new By.ByXPath("//div[@class='showPrice']")).getText()));
        System.out.println(b+"\n");
        double r= Double.parseDouble(b);

        return r;
    }
    static public Double sattrade(String satılacak,WebDriver a){
        String link2=String.format("https://www.binance.com/tr/trade/%s?layout=pro",satılacak);
        a.get(link2);
        String c= a.findElement(new By.ByXPath("//div[@class='showPrice']")).getText();
        System.out.println(c+"\n");
        String[] arrOfStr = c.split(",");
        String t=arrOfStr[0]+arrOfStr[1];
        double tt= Double.parseDouble(t);

        double r=tt;
        return r;
    }


    static public Double contrade(String convert, WebDriver a){
        String link3=String.format("https://www.binance.com/tr/trade/%s?layout=pro",convert);
        a.get(link3);
        String d= String.valueOf(Double.parseDouble(a.findElement(new By.ByXPath("//div[@class='showPrice']")).getText()));
        System.out.println(d+"\n");
        double r= Double.parseDouble(d);
        return r;
    }


    static public ArrayList<Double> para(double anapara, double fist, double twisee, double thrie){
        double vergi=0;
        double komisyon=0.001;
        long alınacaktoken= 0;
        double satılıncakarsıbirim=0;
        double first=fist;
        double twise=twisee;
        double tries=thrie;

        //---------------------------------------
        double ara=anapara/first;
        alınacaktoken=(long) ara;
        anapara=anapara-(first*alınacaktoken);
        vergi=(first*alınacaktoken)*komisyon;
        double elde=(first*alınacaktoken)-vergi;
        alınacaktoken-=alınacaktoken*komisyon;
        System.out.println("alınan token: "+alınacaktoken+"\n"+
                "kalan para: "+anapara+"\n"+
                "harcanan para: "+elde+"\n"+
                "vergi: "+vergi+"\n");

        //--------------------------------------------
        satılıncakarsıbirim+=alınacaktoken*twise;
        vergi=satılıncakarsıbirim*komisyon;
        satılıncakarsıbirim-=vergi;
        alınacaktoken=0;
        System.out.println("alınan karşı birim parası: "+satılıncakarsıbirim+"\n"+
                "vergi: "+vergi+"\n"+
                "yeni değer: "+twise+"\n");
        //---------------------------------------------
        double arabirim=satılıncakarsıbirim/tries;
        anapara+=(long)arabirim;
        satılıncakarsıbirim-=(long)arabirim*tries;
        vergi=(long)arabirim*komisyon;
        anapara-=vergi;
        System.out.println("toplam kalan para: "+anapara+"\n"+
                "vergi:"+vergi+"\n"+
                "kalan karşı para: "+satılıncakarsıbirim+"\n");

        ArrayList<Double> r=new ArrayList<>();
        r.add(anapara);
        r.add(first);
        r.add(twise);
        r.add(tries);
        return r;
    }






    public static void kontrol(double money,ArrayList<Double> a,String sat, WebDriver driver) {


        double b=0;
            if(a.get(0)<=money){
                b=a.get(2)+(a.get(2)*0.001);
                System.out.println("zarar"+"\n"+b);
                kontrol(1000,para(1000,a.get(1),b,a.get(3)),"BNB_TRY",driver);


            }
            else if(a.get(0)>money){
                //trade
                boolean deneme=true;
                while (deneme){
                    if(a.get(2)==sattrade("BNB_TRY",driver)){
                        System.out.println("trade çalışıyor...");
                        System.out.println(a.get(0));
                        System.out.println(a.get(2));
                        deneme=false;
                    }
                }





            }


    }



    static public void secim(String a,WebDriver driver){
        switch(a) {
            case "login":
                fonsiyon.login(driver);
                break;

            case "start":

                for (int i = 0; i < 1; i++) {
                    kontrol(1000,para(1000,altrade("BNB_USDT",driver),sattrade("BNB_TRY",driver),contrade("USDT_TRY",driver)),"BNB_TRY",driver);
                }


                break;

            case "quit":
                fonsiyon.quit(driver);

                break;

            default:
                // code block
        }
    }


    static public void quit(WebDriver driver){
        driver.quit();
    }
}

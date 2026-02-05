package base;

import java.util.*;
import java.io.*;
import org.openqa.selenium.*;

public class Base {
	public static WebDriver driver;
	public String getUrl() throws Exception {
		Properties prop = new Properties();
		prop.load(new FileInputStream("C:\\Users\\Harsh.Vashishth\\eclipse-workspace\\mavenprojectharsh\\src\\main\\java\\data.properties"));
	
		return prop.getProperty("url");
	}
}


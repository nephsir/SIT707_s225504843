package sit707_week2;

import java.io.File;

/**
 * Hello world!
 * @author Ahsan Habib
 */
public class Main 
{
    public static void main( String[] args )
    {
        //SeleniumOperations.officeworks_registration_page("https://www.officeworks.com.au/app/identity/create-account");
        File file = new File("src/main/java/sit707_week2/webpage1.html");
        String fileUrl = file.toURI().toString();
        SeleniumOperations.localFile(fileUrl);
    }
}

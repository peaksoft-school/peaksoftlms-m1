package kg.peaksoft.peaksoftlmsm1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class PeaksoftlmsM1Application {

    public static void main(String[] args) {
        SpringApplication.run(PeaksoftlmsM1Application.class, args);
        System.out.println("Welcome to Peaksoftlms-m1 project");
    }

    @GetMapping("/")
    public String greetingPage() {
        return "<h1>Welcome to Peaksoftlms-m1 application!!!<h1/>";
    }

}

package mk.ukim.finki.wp.lab.bootstrap;
import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Registration;
import mk.ukim.finki.wp.lab.model.Workshop;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Workshop> workshops = null;
    public static List<Registration> registrations = null;

    @PostConstruct
    public void init() {
        workshops = new ArrayList<>();
        workshops.add(new Workshop("Java Basics", "Programming", 1500, 20));
        workshops.add(new Workshop("Spring Boot", "Programming", 2500, 15));
        workshops.add(new Workshop("Thymeleaf", "Web", 1200, 10));
        workshops.add(new Workshop("Docker", "DevOps", 2000, 12));
        workshops.add(new Workshop("Git", "Tools", 800, 30));
        workshops.add(new Workshop("SQL", "Databases", 1800, 25));
        workshops.add(new Workshop("React", "Web", 2200, 18));
        workshops.add(new Workshop("Python", "Programming", 1400, 22));
        workshops.add(new Workshop("UX Design", "Design", 1600, 8));
        workshops.add(new Workshop("Kubernetes", "DevOps", 3000, 0)); // capacity = 0

        registrations = new ArrayList<>();
    }
}

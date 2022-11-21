import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateAdapter{
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

    public String marshal (LocalDate date) throws Exception {
        return date.format(dateFormat);
    }
    public LocalDate unmarshal(String date) {
        return LocalDate.parse(date, dateFormat);
    }
}

import java.time.LocalDate;

public class DaysForMonth {
    private LocalDate localDate;
    private int days;

    public DaysForMonth(LocalDate localDate, int days)
    {
        this.localDate = localDate;
        this.days = days;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}

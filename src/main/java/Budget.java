import java.time.LocalDate;

public class Budget
{
    private LocalDate date;
    private int amount;

    public Budget(LocalDate localDate, int amount)
    {
        date = localDate;
        this.amount = amount;
    }


    public int getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}

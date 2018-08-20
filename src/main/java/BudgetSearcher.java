import java.time.LocalDate;
import java.util.List;

public class BudgetSearcher {

    public int searchByDate(LocalDate localdate, List<Budget> budgetList)
    {
        for (int i = 0; i < budgetList.size(); i++) {
            LocalDate budgetDate = budgetList.get(i).getDate();
            if(budgetDate.getYear() == localdate.getYear()
                    && budgetDate.getMonth() == localdate.getMonth())
            {
                return budgetList.get(i).getAmount();
            }
        }
        return 0;
    }
}

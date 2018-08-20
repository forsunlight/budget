import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BudgetDao
{
    ArrayList<Budget> budgetArrayList = new ArrayList<Budget>();
    List<Budget> findAll()
    {

        budgetArrayList.add(new Budget(LocalDate.of(2018, 1, 1), 310));
        budgetArrayList.add(new Budget(LocalDate.of(2018, 2, 1), 560));
        budgetArrayList.add(new Budget(LocalDate.of(2018, 3, 1), 6000));
        return budgetArrayList;
    }

}

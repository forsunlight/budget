import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BudgetQueryTest {
    BudgetDao budgetDaoStub;
    BudgetQuery budgetQuery;
    ArrayList<Budget> budgetArrayList;

    @Before
    public void setup()
    {
        budgetDaoStub = mock(BudgetDao.class);
        budgetQuery = new BudgetQuery(budgetDaoStub);

        budgetArrayList = new ArrayList<Budget>();
        budgetArrayList.add(new Budget(LocalDate.of(2016, 12, 1), 310));
        budgetArrayList.add(new Budget(LocalDate.of(2017, 3, 1), 310));
        budgetArrayList.add(new Budget(LocalDate.of(2017, 4, 1), 300));
        budgetArrayList.add(new Budget(LocalDate.of(2018, 1, 1), 310));
        budgetArrayList.add(new Budget(LocalDate.of(2018, 2, 1), 560));
        budgetArrayList.add(new Budget(LocalDate.of(2018, 3, 1), 620));
        budgetArrayList.add(new Budget(LocalDate.of(2018, 5, 1), 500));
        budgetArrayList.add(new Budget(LocalDate.of(2019, 1, 1), 310));
        when(budgetDaoStub.findAll()).thenReturn(budgetArrayList);
    }


    @Test
    public void testQuerySameMonth()
    {
        int amount = budgetQuery.query(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 1, 31));
        Assert.assertEquals(310, amount);
    }

    @Test
    public void testQuerySameDay()
    {
        int amount = budgetQuery.query(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 1, 1));
        Assert.assertEquals(10, amount);
    }

    @Test
    public void testQueryCrossMonth()
    {
        int amount = budgetQuery.query(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 2, 15));
        Assert.assertEquals(610, amount);
    }

    @Test
    public void testQueryCorssMonth2()
    {
        int amount = budgetQuery.query(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 3, 15));
        Assert.assertEquals(1170, amount);
    }

    @Test
    public void testQueryNoBudget()
    {
        int amount = budgetQuery.query(LocalDate.of(2018, 1, 1), LocalDate.of(2018, 4, 15));
        Assert.assertEquals(1490, amount);
    }

    @Test
    public void testQueryCrossYear()
    {
        int amount = budgetQuery.query(LocalDate.of(2018, 1, 1), LocalDate.of(2019, 1, 1));
        Assert.assertEquals(2000, amount);
    }


    @Test
    public void testQueryCrossYear2()
    {
        int amount = budgetQuery.query(LocalDate.of(2017, 3, 12), LocalDate.of(2019, 1, 1));
        Assert.assertEquals(2500, amount);
    }


    @Test
    public void testQueryCrossYear3()
    {
        int amount = budgetQuery.query(LocalDate.of(2016, 12, 31), LocalDate.of(2019, 1, 1));
        Assert.assertEquals(2620, amount);
    }
}

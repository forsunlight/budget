import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.time.LocalDate.of;

public class BudgetQuery {
    BudgetDao budgetDao;
    //DateHelper helper = new DateHelper();
    BudgetSearcher searcher = new BudgetSearcher();

    public BudgetQuery(BudgetDao budgetDao)
    {
        this.budgetDao = budgetDao;
    }

    public int query(LocalDate startDate, LocalDate endDate)
    {
        List<DaysForMonth>  budgetDateDaysList = split(startDate, endDate);
        List<Budget> budgetList= budgetDao.findAll();
        int sum = 0;
        for(DaysForMonth daysForMonth : budgetDateDaysList)
        {
            int amountByMonth = searchByDate(daysForMonth.getLocalDate(), budgetList);
            if( daysForMonth.getDays() == daysForMonth.getLocalDate().lengthOfMonth())
            {
                sum += amountByMonth;
            }
            else{
                int amountPerDay = amountByMonth/daysForMonth.getLocalDate().lengthOfMonth();
                sum += amountPerDay * daysForMonth.getDays();
            }
        }
        return sum;
    }


    public List<DaysForMonth> split(LocalDate startDate, LocalDate endDate)
    {
        List<DaysForMonth> daysForMonthList = new ArrayList<>();

        if(YearMonth.from(startDate).equals(YearMonth.from(endDate)))
        {
            daysForMonthList.add(new DaysForMonth(startDate, Period.between(startDate, endDate).getDays() + 1));
            return daysForMonthList;
        }

        DaysForMonth startMonth = new DaysForMonth(startDate, startDate.lengthOfMonth() -  startDate.getDayOfMonth() + 1);
        daysForMonthList.add(startMonth);

        daysForMonthList.add(getDayOfMonth(endDate, endDate.getDayOfMonth()));

        LocalDate date;
        for(date = startDate.plusMonths(1); !YearMonth.from(date).equals(endDate); date = date.plusMonths(1).withDayOfMonth(1))
        {
            DaysForMonth daysForMonth = new DaysForMonth(of(date.getYear(), date.getMonthValue(), 1), Period.between(date.withDayOfMonth(1), date.withDayOfMonth(date.lengthOfMonth())).getDays());
            daysForMonthList.add(daysForMonth);
        }


        /*if(startDate.getYear() == endDate.getYear())
        {
            for(int i = startDate.getMonthValue() + 1 ; i < endDate.getMonthValue() && i < 12; i ++)
            {
                DaysForMonth daysForMonth = new DaysForMonth(of(startDate.getYear(), i, 1), Period.between(of(startDate.getYear(), i, 1), of(startDate.getYear(), i, 1).withDayOfMonth(of(startDate.getYear(), i, 1).lengthOfMonth())).getDays());
                daysForMonthList.add(daysForMonth);
            }
            return daysForMonthList;
        }


        for(int i = startDate.getMonthValue() + 1 ; i <= 12; i ++)
        {
            LocalDate date = of(startDate.getYear(), i, 1);
            daysForMonthList.add(getDayOfMonth(date, date.lengthOfMonth()));
        }

        int startYear = startDate.getYear();
        int endYear = endDate.getYear();

        for(int i = startYear + 1; i < endYear; i ++ )
        {
            for(int j = 1 ; j <= 12; j ++)
            {
                LocalDate date = of(i, j, 1);
                daysForMonthList.add(getDayOfMonth(date, date.lengthOfMonth()));
            }
        }

        for(int i = 1 ; i < endDate.getMonthValue() - 1 && i > 0; i ++)
        {
            LocalDate date = of(endDate.getYear(), i, 1);
            daysForMonthList.add(getDayOfMonth(date, date.lengthOfMonth()));
        }*/

        return daysForMonthList;

    }


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
    private DaysForMonth getDayOfMonth(LocalDate date, int days) {
        return new DaysForMonth(date, days);
    }




}

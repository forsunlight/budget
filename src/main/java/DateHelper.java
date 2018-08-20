import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import static java.time.LocalDate.*;

public class DateHelper {

    public List<DaysForMonth> split(LocalDate startDate, LocalDate endDate)
    {
        List<DaysForMonth> daysForMonthList = new ArrayList<>();
        Period period = Period.between(startDate, endDate);
        if(startDate.getYear() == endDate.getYear()
                && startDate.getMonth() == endDate.getMonth())
        {
            daysForMonthList.add(new DaysForMonth(startDate, period.getDays() + 1));
            return daysForMonthList;
        }

        DaysForMonth startMonth = new DaysForMonth(startDate, startDate.lengthOfMonth() -  startDate.getDayOfMonth() + 1);
        daysForMonthList.add(startMonth);

        if(startDate.getYear() == endDate.getYear())
        {
            for(int i = startDate.getMonthValue() + 1 ; i < endDate.getMonthValue() && i < 12; i ++)
            {
                LocalDate date = of(startDate.getYear(), i, 1);
                DaysForMonth daysForMonth = new DaysForMonth(date, date.lengthOfMonth());
                daysForMonthList.add(daysForMonth);
            }
        }
        else
        {
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
            }
        }

        daysForMonthList.add(getDayOfMonth(endDate, endDate.getDayOfMonth()));
        return daysForMonthList;
    }

    private DaysForMonth getDayOfMonth(LocalDate date, int days) {
        return new DaysForMonth(date, days);
    }

}

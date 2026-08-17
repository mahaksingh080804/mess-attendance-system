package studentregistrationmodule;
import java.time.LocalDate;

public class DailyMealReport {

    private LocalDate date;
    private int breakfastCount;
    private int lunchCount;
    private int dinnerCount;

    public DailyMealReport(LocalDate date, int breakfastCount, int lunchCount, int dinnerCount){

        this.date = date;
        this.breakfastCount = breakfastCount;
        this.lunchCount = lunchCount;
        this.dinnerCount = dinnerCount;

    }

        public LocalDate getDate() {
            return date;
        }

        public int getBreakfastCount() {
            return breakfastCount;
        }

        public int getLunchCount() {
            return lunchCount;
        }

        public int getDinnerCount() {
            return dinnerCount;
        }

}

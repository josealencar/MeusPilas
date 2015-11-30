package josealencar.com.br.meuspilas.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jose on 25/11/15.
 */
public class ServiceHistory {
    private long userId;
    private int dayServiceRoutine;
    private int monthServiceRoutine;

    public ServiceHistory(long userId, int dayServiceRoutine, int monthServiceRoutine) {
        this.userId = userId;
        this.dayServiceRoutine = dayServiceRoutine;
        this.monthServiceRoutine = monthServiceRoutine;
    }

    public int getDayServiceRoutine() {
        return dayServiceRoutine;
    }

    public int getMonthServiceRoutine() {
        return monthServiceRoutine;
    }

    public long getUserId() {
        return userId;
    }
}

package josealencar.com.br.meuspilas.service;

import java.util.Calendar;

import josealencar.com.br.meuspilas.dao.Db4oHelper;
import josealencar.com.br.meuspilas.model.ServiceHistory;

/**
 * Created by jose on 25/11/15.
 */
public class MainService {
    private FixedAccountService fixedAccountService;
    private IncomeService incomeService;
    private LoanService loanService;
    private OutcomeService outcomeService;
    private SalaryService salaryService;
    private ServiceHistoryService serviceHistoryService;
    private UserService userService;
    private VariableAccountService variableAccountSevice;

    public MainService(Db4oHelper db4o) {
        this.fixedAccountService = new FixedAccountService(db4o);
        this.incomeService = new IncomeService(db4o);
        this.loanService = new LoanService(db4o);
        this.outcomeService = new OutcomeService(db4o);
        this.salaryService = new SalaryService(db4o);
        this.serviceHistoryService = new ServiceHistoryService(db4o);
        this.userService = new UserService(db4o);
        this.variableAccountSevice = new VariableAccountService(db4o);
    }

    public void doCheckRoutine(final long userId) {
        ServiceHistory serviceHistory = serviceHistoryService.findLastHistoryFromUser(userId);
        if (serviceHistory == null) {
            Calendar toService = Calendar.getInstance();
            toService.set(Calendar.DAY_OF_MONTH, toService.getActualMinimum(Calendar.DAY_OF_MONTH));
            serviceHistory = new ServiceHistory(userId, toService.get(Calendar.DAY_OF_MONTH), toService.get(Calendar.MONTH));
        }
        int day = serviceHistory.getDayServiceRoutine();
        int month = serviceHistory.getMonthServiceRoutine();
        Calendar today = Calendar.getInstance();
        int thisDay = today.get(Calendar.DAY_OF_MONTH);
        int thisMonth = today.get(Calendar.MONTH);
        if (day < thisDay && month == thisMonth) {
            fixedAccountService.verifyNewEntry(userId, day, thisDay);
            loanService.verifyNewEntry(userId, day, thisDay);
            salaryService.verifyNewEntry(userId, day, thisDay);
            variableAccountSevice.verifyNewEntry(userId, day, thisDay);
            serviceHistory = new ServiceHistory(userId, thisDay, thisMonth);
            serviceHistoryService.save(serviceHistory);
        }
    }
}

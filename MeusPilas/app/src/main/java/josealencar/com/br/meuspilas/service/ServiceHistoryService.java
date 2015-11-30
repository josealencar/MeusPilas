package josealencar.com.br.meuspilas.service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import josealencar.com.br.meuspilas.dao.Db4oHelper;
import josealencar.com.br.meuspilas.dao.ServiceHistoryDao;
import josealencar.com.br.meuspilas.model.ServiceHistory;

/**
 * Created by jose on 25/11/15.
 */
public class ServiceHistoryService {
    private ServiceHistoryDao serviceHistoryDao;

    public ServiceHistoryService(final Db4oHelper db4o) {
        this.serviceHistoryDao = new ServiceHistoryDao(db4o);
    }

    public void save(ServiceHistory serviceHistory) {
        serviceHistoryDao.save(serviceHistory);
    }

    public ServiceHistory findById(final long id) {
        return serviceHistoryDao.findById(id);
    }

    public List<ServiceHistory> findByUserId(final long userId) {
        return serviceHistoryDao.findByUserId(userId);
    }

    public long getId(ServiceHistory serviceHistory) {
        return serviceHistoryDao.getId(serviceHistory);
    }

    public ServiceHistory findLastHistoryFromUser(final long userId) {
        List<ServiceHistory> serviceHistories = findByUserId(userId);
        if (serviceHistories.isEmpty()) {
            return null;
        }
        return serviceHistories.get(serviceHistories.size() - 1);
    }

    public int findLastDayHistoryFromUser(final long userId) {
        ServiceHistory serviceHistory = findLastHistoryFromUser(userId);
        return serviceHistory == null ? serviceHistory.getDayServiceRoutine() : Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH);
    }
}

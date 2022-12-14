package internship.paymentSystem.backend.services.interfaces;

import internship.paymentSystem.backend.models.Person;
import internship.paymentSystem.backend.models.PersonHistory;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IPersonHistoryService {

    PersonHistory savePersonHistory(Person person);

    List<PersonHistory> getHistoryOfPersons();

    List<PersonHistory> getHistoryByPersonId(Long id);

    List<PersonHistory> getPersonsHistoryOfUser(Long currentUserId);

    PersonHistory getLastVersionOfPerson(Long personId);

    List<PersonHistory> getPersonState(LocalDateTime timestamp);

}

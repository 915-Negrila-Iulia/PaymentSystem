package internship.paymentSystem.backend.services.interfaces;

import internship.paymentSystem.backend.models.Audit;
import internship.paymentSystem.backend.models.enumerations.ObjectTypeEnum;

import java.util.List;

public interface IAuditService {

    Audit saveAudit(Audit audit);

    List<Audit> getAudit();

    List<Audit> getAuditOfObject(Long objectId, ObjectTypeEnum objectType);

    Long getUserThatMadeUpdates(Long objectId, ObjectTypeEnum objectType);

}

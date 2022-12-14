package internship.paymentSystem.backend.models;

import internship.paymentSystem.backend.models.enums.ObjectTypeEnum;
import internship.paymentSystem.backend.models.enums.OperationEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "object_id")
    private Long objectID;

    @Column(name = "object_type")
    @Enumerated(EnumType.STRING)
    private ObjectTypeEnum objectType;

    @Column(name = "operation")
    @Enumerated(EnumType.STRING)
    private OperationEnum operation;

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "timestamp")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime timestamp;

    public Audit(Long objectID, ObjectTypeEnum objectType, OperationEnum operation, Long userID) {
        this.objectID = objectID;
        this.objectType = objectType;
        this.operation = operation;
        this.userID = userID;
    }

}

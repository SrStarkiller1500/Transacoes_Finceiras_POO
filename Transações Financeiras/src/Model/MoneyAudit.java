package Model;
import java.time.OffsetDateTime;
import java.util.UUID;

public record MoneyAudit(UUID transactionid, BankService targetService, String description, OffsetDateTime createdATime) {

    public OffsetDateTime getCreatedAt() {
    return createdATime;
}

} 
package communication.magalu.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_communication")
public class Magalu implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "schedule_time")
    private LocalDateTime scheduledTime;
    @Column(nullable = false, name = "recipient")
    private String recipient;
    @Column(name = "message")
    private String message;
    @Column(name = "type")
    private String type;
    @Column(name = "status")
    private String status;

    public Magalu(){
    }

    public Magalu(Long id, LocalDateTime scheduledTime, String recipient, String message, String type, String status) {
        this.id = id;
        this.scheduledTime = scheduledTime;
        this.recipient = recipient;
        this.message = message;
        this.type = type;
        this.status = status;
    }

    public Magalu(String recipient, String message, String type) {
        this.recipient = recipient;
        this.message = message;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Magalu that = (Magalu) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


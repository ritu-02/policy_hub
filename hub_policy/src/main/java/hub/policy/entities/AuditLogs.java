package hub.policy.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="audit_logs")
@NoArgsConstructor
@Getter
@Setter
@ToString

public class AuditLogs {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="log_id")
	private Long logId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="user_Id",nullable = false)
	@NotNull(message="userId cannot be null")
	private User userId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="action_type")
	private ActionType actionType;
	
	@Column(name="action_details")
	private String actionDetails;
	
	@Column(name="action_date")
	@CreationTimestamp
	private LocalDateTime actionDate;
		
}

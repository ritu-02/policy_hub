package hub.policy.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

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

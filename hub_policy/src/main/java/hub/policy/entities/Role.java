package hub.policy.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long Id;

@Enumerated(EnumType.STRING)
@Column(unique = true,length=50)
private UserRole roleName;


//@ManyToOne
//@JoinColumn(name = "user_id")
// // Break the loop here
//private User user;
@JsonIgnore
@ManyToMany(mappedBy = "userRoles")
private Set<User> users = new HashSet<>();


}

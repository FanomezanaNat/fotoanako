package mu.mcb.fotoanako.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "\"user\"")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String lastName;
  private String firstName;
  private String email;
  private String password;
  @Enumerated(EnumType.STRING)
  private UserRole role;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "agenda_id", referencedColumnName = "id")
  private Agenda agenda;
}

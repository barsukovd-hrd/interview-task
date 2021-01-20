package interviewtask.tms.domain;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usr")
@Entity
@EqualsAndHashCode(exclude = {"authorOf", "assignedTo", "comments"})
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private boolean deleted;

  @ManyToOne
  @JoinColumn(name = "department_id")
  private Department department;

  @OneToMany(mappedBy = "author")
  private Set<Task> authorOf;

  @OneToMany(mappedBy = "assignee")
  private Set<Task> assignedTo;

  @OneToMany(mappedBy = "author")
  private Set<Comment> comments;
}

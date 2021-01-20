package interviewtask.tms.domain;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "tasks")
@Entity
@EqualsAndHashCode(exclude = {"comments", "attachments"})
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private boolean deleted;

  private String theme;

  private String description;

  @Enumerated(EnumType.STRING)
  private Status status;

  @Column(name = "created_date")
  private LocalDateTime createdDate;

  @ManyToOne
  @JoinColumn(name = "author_id")
  private User author;

  @ManyToOne
  @JoinColumn(name = "assignee_id")
  private User assignee;

  @OneToMany(mappedBy = "task")
  private Set<Comment> comments;

  @OneToMany(mappedBy = "task")
  private Set<Attachment> attachments;
}

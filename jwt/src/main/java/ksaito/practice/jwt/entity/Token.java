package ksaito.practice.jwt.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "token")
public class Token {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "user_id")
  private Integer userId;
  @Column(name = "typ")
  private String type;
  @Column(name = "alg")
  private String algorithm;
  @Column(name = "iss")
  private String issuer;
  @Column(name = "sub")
  private String subject;
  @Column(name = "name")
  private String name;
  @Column(name = "iat")
  private String issuerAt;
  @Column(name = "exp")
  private String expireAt;
  @Column(name = "created_at")
  private Timestamp createdAt;
  @Column(name = "updated_at")
  private Timestamp updatedAt;
}

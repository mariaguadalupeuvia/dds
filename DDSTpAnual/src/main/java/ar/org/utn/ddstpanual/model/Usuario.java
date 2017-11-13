package ar.org.utn.ddstpanual.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USUARIO")
@AllArgsConstructor
@NoArgsConstructor
public @Data class Usuario {

  @Id
  @GeneratedValue
  private int id;
  private String nombre;
  private String password;

  public Usuario(String nombre, String password) {
    this.nombre = nombre;
    this.password = password;
  }

}

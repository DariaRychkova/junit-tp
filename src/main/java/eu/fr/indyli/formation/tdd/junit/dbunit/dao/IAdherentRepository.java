package eu.fr.indyli.formation.tdd.junit.dbunit.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import eu.fr.indyli.formation.tdd.junit.dbunit.entity.Adherent;

@Repository("adherentRepository")
public interface IAdherentRepository extends JpaRepository<Adherent, Long> {

  public Optional<Adherent> findByLastname(String lastname);
  
  /**
   * Found Adherent GreatherThan
   * @param age
   * @return
   */
  @Query("SELECT a FROM Adherent a WHERE a.age > :age")
  public List<Adherent> findByAgeGreaterThan(@Param("age") Integer age);
  
  @Query("select count(a) from Adherent a")
  public Integer findNumberOfAdherent();

}

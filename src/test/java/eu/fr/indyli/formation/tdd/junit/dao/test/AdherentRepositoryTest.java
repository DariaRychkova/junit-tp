package eu.fr.indyli.formation.tdd.junit.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Objects;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import eu.fr.indyli.formation.tdd.junit.dbunit.app.Application;
import eu.fr.indyli.formation.tdd.junit.dbunit.dao.IAdherentRepository;
import eu.fr.indyli.formation.tdd.junit.dbunit.entity.Adherent;

/** it is preferable to use an abstract class. Here for example only. */
@DatabaseSetup("/adherent.xml")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestExecutionListeners({
    TransactionalTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class
})
public class AdherentRepositoryTest {

  @Resource(name = "adherentRepository")
  private IAdherentRepository adherentRepository;

  @Test
  public void findByName_WhenExist_ShouldBeExist() {
    // given
    Adherent expected = new Adherent("PHILIPPE", "EDOUARD", 45);
    expected.setId(1L);

    // when
    Optional<Adherent> actual = adherentRepository.findByLastname("PHILIPPE");

    // then
    assertTrue(Optional.of(expected).equals(actual));
  }

  @Test
  public void findByName_WhenNotExist_ShouldBeNull() {
    // when
    Optional<Adherent> actual = adherentRepository.findByLastname("NotExist");

    // then
    assertTrue(Optional.empty().equals(actual));
  }

  /**
   * TODO 3: Test d'effectivite de l'ajout d'un Adherent
   */
  @Test
  public void createNew_ShouldBeMore() {
	  // given
	    Adherent employee = new Adherent("John", "admin", 72);

	    // when
	    adherentRepository.save(employee);

	    // then
	    assertFalse(Objects.isNull(employee.getId()));
  }


  /**
   * TODO 4: Test d'effectivit� de la mise a jour de l'age
   */
  @Test
  public void update_age_ShouldBeEffective() {
    // given
    Long id = 2L;
    Adherent ad = null;

    // when
    Integer ageAfter = 45;
    
    Optional<Adherent> existingAdherentOptional = adherentRepository.findById(id);
    assertTrue(existingAdherentOptional.isPresent());
    
    Adherent existingAdherent = existingAdherentOptional.get();
    existingAdherent.setAge(ageAfter);
    adherentRepository.save(existingAdherent);


    // then
    Optional<Adherent> updatedAdherentOptional = adherentRepository.findById(id);
    assertTrue(updatedAdherentOptional.isPresent());
    
    Adherent updatedAdherent = updatedAdherentOptional.get();
    assertEquals(ageAfter, updatedAdherent.getAge());
  }


  @Test
  public void delete_By_Id_WhenExist_ShouldBeDelete() {
    // given
    Long idAdherent = 2L;
    Integer sizeBefore = this.adherentRepository.findAll().size();
 
    // when
    this.adherentRepository.deleteById(idAdherent);
    Integer sizeAfter = this.adherentRepository.findNumberOfAdherent();
    Integer sizeNew= sizeAfter + 1;
    // then
    assertEquals(sizeBefore, sizeNew);
  }

}

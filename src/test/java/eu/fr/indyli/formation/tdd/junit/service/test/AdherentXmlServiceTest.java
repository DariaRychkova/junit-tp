package eu.fr.indyli.formation.tdd.junit.service.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
import eu.fr.indyli.formation.tdd.junit.dbunit.entity.Adherent;
import eu.fr.indyli.formation.tdd.junit.dbunit.service.IAdherentService;
@DatabaseSetup("/adherent.xml")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@TestExecutionListeners({
        TransactionalTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class AdherentXmlServiceTest {

    @Resource(name = "adherentService")
    private IAdherentService adherentService;
    
    @Test
    public void testFindEmployeeGreaterThanWithSuccess() {
    	//Given
    	Integer findAge = 50;
    	Integer nbHavingAge = 2;
    	
    	//When
    	List<Adherent> adherentsPlus50 = this.adherentService.findByAgeGreaterThan(findAge);
    	Integer sizeAdherentPlus50 = adherentsPlus50.size();
    	
    	//Then
    	assertEquals(nbHavingAge, sizeAdherentPlus50);
    }


}

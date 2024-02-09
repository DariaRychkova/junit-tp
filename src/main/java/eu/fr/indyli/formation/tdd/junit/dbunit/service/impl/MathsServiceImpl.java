package eu.fr.indyli.formation.tdd.junit.dbunit.service.impl;

import eu.fr.indyli.formation.tdd.junit.dbunit.service.IMathsService;
import eu.fr.indyli.formation.tdd.junit.dbunit.service.exception.MathException;

public class MathsServiceImpl implements IMathsService {

  public Integer sum(Integer number1, Integer number2) {
    return Math.addExact(number1, number2);
  }

  public Integer substract(Integer number1, Integer number2) { 
    return Math.subtractExact(number1, number2);
  }

  public Integer multiply(Integer number1, Integer number2) {
    return Math.multiplyExact(number1, number2);
  }

  public Integer divide(Integer number1, Integer number2) throws MathException {
    return Math.floorDiv(number1, number2);
  }

  public Boolean isPrimeNumber(Integer numP) throws MathException {
	  if (numP < 0) {
	      throw new MathException("Le nombre ne peut etre negatif");
	    }
	    Integer compteur = 2; 
	    Boolean isPrimeNumber = Boolean.TRUE;
	    while (compteur < numP && isPrimeNumber) {
	      if (numP % compteur == 0) {
	        isPrimeNumber = false;
	        break;
	      }
	      compteur++;
	    }
	    return isPrimeNumber;
  }

  public Integer sumOfNFirstPrimeNumber(Integer numP) throws MathException {
    // TODO 2 : Codez cette fonction
	  if (numP < 0) {
	      throw new MathException("Le nombre ne peut etre negatif");
	    }
	  Integer sum = 0;
	  Integer count= 2;
	  while (count <= numP) {
		  if(Boolean.TRUE.equals(this.isPrimeNumber(count))) {
			  sum += count;
		  }
		  count ++;
	  }
	 
    return sum;
  }

  public Integer average(Integer number1, Integer number2) {
    return (number1 + number2) / 2;
  }

  public Integer factorial(Integer numP) throws MathException {
    if (numP < 0) {
      throw new MathException("Le numbre ne peut �tre n�gatif");
    }
    if (numP == 0)
      return (1);
    else
      return (numP * factorial(numP - 1));
  }

}

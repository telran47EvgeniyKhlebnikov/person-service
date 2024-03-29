package telran.java47.person.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.java47.person.dto.CityPopulationDto;
import telran.java47.person.model.Child;
import telran.java47.person.model.Employee;
import telran.java47.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	
//	@Query("select p from Person p where p.name=?1")
	Stream<Person> findByNameIgnoreCase(String name);
	
//	@Query("select p from Person p where p.address.city=:cityName")
	Stream<Person> findByAddressCityIgnoreCase(@Param("cityName") String city);
	
	Stream<Person> findByBirthDateBetween(LocalDate from, LocalDate to);
	
	@Query("select new telran.java47.person.dto.CityPopulationDto(p.address.city, count(p)) from Person p group by p.address.city order by count(p) desc")
	List<CityPopulationDto> getCitiesPopulation();
	
//	@Query("select e from Employee e where e.salary between ?1 and ?2")
	Stream<Employee> findBySalaryBetween(int min, int max);

//	@Query("select c from Child c")
	Stream<Child> findChildrenBy();

}

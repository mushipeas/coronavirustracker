package com.mushipeas.coronavirustracker;

import com.mushipeas.coronavirustracker.services.CoronavirusDataService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

// @ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class CoronavirusDataServiceTest {
     
    private static CoronavirusDataService coronavirusDataService;
 
    @BeforeAll
    static void setUp() {
        coronavirusDataService = new CoronavirusDataService();
        coronavirusDataService.fetchCovidData();
    }
    
    @Test
    void fetchCovidDataTest_success() {
        // TODO The fetch function either needs to be moved to a DAO or properly tested here
        assertThat(coronavirusDataService.getAllLocationStats()).isNotNull();
    }


    @Test
    void getAllLocationStatsTest() {
        assertThat(coronavirusDataService.getAllLocationStats()).isNotNull();
    }

    
    @Test
    void getTotalReportedCasesTest() {
        assertThat(coronavirusDataService.getTotalReportedCases()).isGreaterThan(0);
    }
    
    @Test
    void getNewCasesTodayTest() {
        assertThat(coronavirusDataService.getNewCasesToday()).isGreaterThan(0);
    }


    // @Test
    // public void getAllEmployeesTest()
    // {
    //     List<EmployeeVO> list = new ArrayList<EmployeeVO>();
    //     EmployeeVO empOne = new EmployeeVO(1, "John", "John", "howtodoinjava@gmail.com");
    //     EmployeeVO empTwo = new EmployeeVO(2, "Alex", "kolenchiski", "alexk@yahoo.com");
    //     EmployeeVO empThree = new EmployeeVO(3, "Steve", "Waugh", "swaugh@gmail.com");
         
    //     list.add(empOne);
    //     list.add(empTwo);
    //     list.add(empThree);
         
    //     when(dao.getEmployeeList()).thenReturn(list);
         
    //     //test
    //     List<EmployeeVO> empList = manager.getEmployeeList();
         
    //     assertEquals(3, empList.size());
    //     verify(dao, times(1)).getEmployeeList();
    // }
     
    // @Test
    // public void getEmployeeByIdTest()
    // {
    //     when(dao.getEmployeeById(1)).thenReturn(new EmployeeVO(1,"Lokesh","Gupta","user@email.com"));
         
    //     EmployeeVO emp = manager.getEmployeeById(1);
         
    //     assertEquals("Lokesh", emp.getFirstName());
    //     assertEquals("Gupta", emp.getLastName());
    //     assertEquals("user@email.com", emp.getEmail());
    // }
     
    // @Test
    // public void createEmployeeTest()
    // {
    //     EmployeeVO emp = new EmployeeVO(1,"Lokesh","Gupta","user@email.com");
         
    //     manager.addEmployee(emp);
         
    //     verify(dao, times(1)).addEmployee(emp);
    // }
    
}

package java_ci_cd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeControllerTest {

    private EmployeeController controller;

    @BeforeEach
    void setUp() {
        controller = new EmployeeController();
    }

    @Test
    void shouldReturnTwoInitialEmployees() {
        assertEquals(2, controller.getAllEmployees().size());
    }

    @Test
    void shouldCreateEmployee() {
        EmployeeController.Employee employee =
                new EmployeeController.Employee(null, "Rahul", "Manager");

        EmployeeController.Employee saved =
                controller.createEmployee(employee);

        assertNotNull(saved.id());
        assertEquals("Rahul", saved.name());
        assertEquals("Manager", saved.role());
    }

    @Test
    void shouldFindEmployeeById() {
        EmployeeController.Employee employee =
                controller.getEmployee(1L);

        assertEquals("Hemanth", employee.name());
    }

    @Test
    void shouldDeleteEmployee() {
        controller.deleteEmployee(1L);

        assertEquals(1, controller.getAllEmployees().size());
    }
}

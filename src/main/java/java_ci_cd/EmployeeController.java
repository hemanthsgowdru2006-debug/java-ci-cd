
package java_ci_cd;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final Map<Long, Employee> employees =
            new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public EmployeeController() {
        addEmployee(new Employee(null, "Hemanth", "Developer"));
        addEmployee(new Employee(null, "Sanjay", "Tester"));
    }

    @GetMapping
    public Collection<Employee> getAllEmployees() {
        return employees.values();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        Employee employee = employees.get(id);
        if (employee == null) {
            throw new NoSuchElementException("Employee not found: " + id);
        }
        return employee;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return addEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        if (employees.remove(id) == null) {
            throw new NoSuchElementException("Employee not found: " + id);
        }
        return "Employee deleted successfully";
    }

    private Employee addEmployee(Employee employee) {
        long id = idGenerator.incrementAndGet();
        Employee saved = new Employee(id, employee.name(), employee.role());
        employees.put(id, saved);
        return saved;
    }

    public record Employee(Long id, String name, String role) {}
}

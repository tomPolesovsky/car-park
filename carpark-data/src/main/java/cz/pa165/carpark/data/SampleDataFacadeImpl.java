package cz.pa165.carpark.data;

import cz.pa165.carpark.persistence.entity.Employee;
import cz.pa165.carpark.persistence.entity.Reservation;
import cz.pa165.carpark.persistence.entity.ReservationSettings;
import cz.pa165.carpark.persistence.entity.Vehicle;
import cz.pa165.carpark.persistence.enums.ReservationStatus;
import cz.pa165.carpark.persistence.enums.UserRole;
import cz.pa165.carpark.service.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * The sample data facade's implementation.
 *
 * @author Jana Applova, 422352@mail.muni.cz
 * @author Tomáš Polešovský, polesovsky.tomas@gmail.com
 */
@Transactional
@Service
public class SampleDataFacadeImpl implements SampleDataFacade {

    private EmployeeService employeeService;
    private VehicleService vehicleService;
    private ReservationService reservationService;
    private ReservationSettingsService reservationSettingsService;

    private List<Employee> sampleEmployees = new ArrayList<>();
    private List<Vehicle> sampleVehicles = new ArrayList<>();

    @Inject
    public SampleDataFacadeImpl(EmployeeService employeeService,
                                VehicleService vehicleService,
                                ReservationService reservationService,
                                ReservationSettingsService reservationSettingsService) {
        this.employeeService = employeeService;
        this.vehicleService = vehicleService;
        this.reservationService = reservationService;
        this.reservationSettingsService = reservationSettingsService;
    }

    @Override
    public void loadData() {
        createEmployees();
        createVehicles();
        createReservationSettings();
        createReservations();
    }

    private void createEmployees() {
        createEmployee("Petr", "Kellner", "CEO",
                "admin@company.cz", "admin", "admin", UserRole.APPROVER);
        createEmployee("Jana", "Applova", ".NET developer",
                "422352@mail.muni.cz", "jana", "jana", UserRole.USER);
        createEmployee("Tomas", "Polesovsky", "backend developer",
                "polesovsky.tomas@gmail.com", "tomas", "tomas", UserRole.USER);
        createEmployee("Ondrej", "Svoren", "frontend developer",
                "487558@mail.muni.cz", "ondrej", "ondrej", UserRole.USER);
        createEmployee("Lojza", "Skubanek", "marketing",
                "kokos@mail.muni.cz", "lojza", "lojza", UserRole.USER);
    }

    private void createEmployee(String firstName, String lastName, String position, String email, String username,
                                String password, UserRole role){
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setPosition(position);
        employee.setEmail(email);
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setRole(role);
        employeeService.save(employee);
        sampleEmployees.add(employee);
    }

    private void createVehicles() {
        createVehicle("BMW", "4A2 3000","sedan", "red", 200000L);
        createVehicle("Bugatti", "DD0 0529","hatchback", "blue", 5500L);
        createVehicle("Audi", "2BF 0496","cabriolet", "red", 99569L);
        createVehicle("Audi", "2FG 3355","liftback", "grey", 154001L);
        createVehicle("BMW", "3RT 5894","sedan", "white", 40000L);
        createVehicle("Citroën", "8L9 0622","roadster", "white", 100010L);
        createVehicle("Citroën", "9C7 0846","hatchback", "white", 111000L);
    }

    private void createVehicle(String brand, String registrationNumber, String type, String color, Long mileage) {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(brand);
        vehicle.setRegistrationNumber(registrationNumber);
        vehicle.setType(type);
        vehicle.setColor(color);
        vehicle.setMileage(mileage);
        vehicleService.save(vehicle);
        sampleVehicles.add(vehicle);
    }

    private void createReservationSettings() {
        createSettings(sampleEmployees.get(0), true, true);
        createSettings(sampleEmployees.get(1), false, true);
        createSettings(sampleEmployees.get(2), true, false);
        createSettings(sampleEmployees.get(3), true, false);
        createSettings(sampleEmployees.get(4), false, false);
    }

    private void createSettings(Employee employee, boolean allowed, boolean autoApproval) {
        ReservationSettings reservationSettings = new ReservationSettings();
        reservationSettings.setEmployee(employee);
        reservationSettings.setAllowed(allowed);
        reservationSettings.setAutoApproval(autoApproval);
        reservationSettingsService.save(reservationSettings);
    }

    private void createReservations() {
        createReservation(LocalDateTime.of(2019, Month.JANUARY, 18, 7, 45),
                LocalDateTime.of(2019, Month.JANUARY, 20, 19, 45),
                sampleEmployees.get(0), sampleVehicles.get(0), ReservationStatus.NEW);
        createReservation(LocalDateTime.of(2019, Month.FEBRUARY, 8, 5, 45),
                LocalDateTime.of(2019, Month.FEBRUARY, 20, 17, 0),
                sampleEmployees.get(1), sampleVehicles.get(4), ReservationStatus.NEW);
        createReservation(LocalDateTime.of(2019, Month.JANUARY, 16, 7, 0),
                LocalDateTime.of(2019, Month.JANUARY, 20, 17, 0),
                sampleEmployees.get(4), sampleVehicles.get(4), ReservationStatus.NEW);
        createReservation(LocalDateTime.of(2019, Month.JANUARY, 20, 7, 0),
                LocalDateTime.of(2019, Month.JANUARY, 22, 16, 0),
                sampleEmployees.get(2), sampleVehicles.get(6), ReservationStatus.NEW);
        createReservation(LocalDateTime.of(2019, Month.JANUARY, 23, 8, 0),
                LocalDateTime.of(2019, Month.JANUARY, 25, 19, 0),
                sampleEmployees.get(2), sampleVehicles.get(3), ReservationStatus.NEW);
        createReservation(LocalDateTime.of(2019, Month.JANUARY, 28, 10, 0),
                LocalDateTime.of(2019, Month.JANUARY, 28, 14, 0),
                sampleEmployees.get(2), sampleVehicles.get(2), ReservationStatus.NEW);
        createReservation(LocalDateTime.of(2019, Month.FEBRUARY, 16, 12, 0),
                LocalDateTime.of(2019, Month.FEBRUARY, 16, 18, 0),
                sampleEmployees.get(2), sampleVehicles.get(5), ReservationStatus.NEW);
        createReservation(LocalDateTime.of(2019, Month.FEBRUARY, 18, 7, 45),
                LocalDateTime.of(2019, Month.FEBRUARY, 20, 19, 45),
                sampleEmployees.get(2), sampleVehicles.get(3), ReservationStatus.NEW);
        createReservation(LocalDateTime.of(2019, Month.JANUARY, 19, 12, 0),
                LocalDateTime.of(2019, Month.JANUARY, 21, 19, 0),
                sampleEmployees.get(3), sampleVehicles.get(1), ReservationStatus.NEW);
        createReservation(LocalDateTime.of(2019, Month.JANUARY, 22, 7, 0),
                LocalDateTime.of(2019, Month.JANUARY, 26, 11, 0),
                sampleEmployees.get(3), sampleVehicles.get(1), ReservationStatus.NEW);
        createReservation(LocalDateTime.of(2019, Month.JANUARY, 27, 11, 0),
                LocalDateTime.of(2019, Month.JANUARY, 27, 15, 0),
                sampleEmployees.get(3), sampleVehicles.get(6), ReservationStatus.NEW);
        createReservation(LocalDateTime.of(2019, Month.FEBRUARY, 16, 10, 15),
                LocalDateTime.of(2019, Month.FEBRUARY, 16, 20, 0),
                sampleEmployees.get(3), sampleVehicles.get(2), ReservationStatus.NEW);
        createReservation(LocalDateTime.of(2019, Month.FEBRUARY, 18, 10, 45),
                LocalDateTime.of(2019, Month.FEBRUARY, 20, 19, 45),
                sampleEmployees.get(3), sampleVehicles.get(5), ReservationStatus.NEW);
        createReservation(LocalDateTime.of(2019, Month.FEBRUARY, 26, 10, 0),
                LocalDateTime.of(2019, Month.FEBRUARY, 28, 19, 0),
                sampleEmployees.get(3), sampleVehicles.get(3), ReservationStatus.NEW);

    }

    private void createReservation(LocalDateTime from, LocalDateTime to, Employee employee, Vehicle vehicle,
                                    ReservationStatus status) {
        Reservation reservation = new Reservation();
        reservation.setFrom(from);
        reservation.setTo(to);
        reservation.setEmployee(employee);
        reservation.setVehicle(vehicle);
        reservation.setStatus(status);
        reservationService.processRequest(reservation, reservationSettingsService.findByEmployee(employee));
    }
}

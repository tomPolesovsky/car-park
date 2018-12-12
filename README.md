##### Masaryk University, PA165 Project

## Car Park
### Official assignment from IS:
The project should emulate a real situation in a company. The company employees can apply for rental of a car. The rental is applied to a given date. Based on such application, the system will offer list of free cars and the system allows reservation of a car.

### Run the application with:
<code>mvn clean install && cd carpark-web && mvn cargo:run</code>

Our application is then available on
<code>http://localhost:8080/pa165</code>.

### REST API:
#### Employees
To get all the employees use:
<code>GET localhost:8080/pa165/rest/employees</code>

To get an employee with specified id use:
<code>GET localhost:8080/pa165/rest/employees/{id}</code>

To get an employee with specified username use:
<code>GET localhost:8080/pa165/rest/employees/find-by-username/{username}</code>

#### Vehicles
To get all the vehicles use:
<code>localhost:8080/pa165/rest/vehicles</code>

To get a vehicle with specified id use:
<code>GET localhost:8080/pa165/rest/vehicles/{id}</code>

To get a vehicle with specified registration number use:
<code>GET localhost:8080/pa165/rest/vehicles/find-by-registration-number/{registration-number}</code>

#### Reservations
To get all the reservations use:
<code>localhost:8080/pa165/rest/reservations</code>

#### ReservationSettings
To get all the reservation settings use:
<code>localhost:8080/pa165/rest/reservation-settings</code>

To get a reservation setting with specified id use:
<code>GET localhost:8080/pa165/rest/reservation-settings/{id}</code>

#### Login

### Collaborators:
Polešovský, T.;
Applová, J.;
Svoreň, O.

*We use pull requests for our changes on the project. Tomas Polesovsky is our team lead and also a reviewer of the requests.*

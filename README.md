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

To add an employee use:  
<code>POST localhost:8080/pa165/rest/employees</code>
and request body:  
```
{  
    "firstName": "Jmeno",  
    "lastName": "Prijmeni",  
    "username": "jmeno",  
    "email": "jmeno@test.cz",  
    "password": "test",  
    "position": "tester"     
}
```

To update an employee use:  
<code>PUT localhost:8080/pa165/rest/employees</code>
and request body:  
```
{
    "id": 1,
    "firstName": "Jmeno",
    "lastName": "Prijmeni",
    "username": "mojejmeno",
    "email": "jmeno@test.cz",
    "password": "test",    
    "position": "tester"
}
```

To delete an employee with specified id use:  
<code>DELETE localhost:8080/pa165/rest/employees/{id}</code>

#### Vehicles
To get all the vehicles use:  
<code>localhost:8080/pa165/rest/vehicles</code>

To get a vehicle with specified id use:  
<code>GET localhost:8080/pa165/rest/vehicles/{id}</code>

To get a vehicle with specified registration number use:  
<code>GET localhost:8080/pa165/rest/vehicles/find-by-registration-number/{registration-number}</code>

To add a vehicle use:  
<code>POST localhost:8080/pa165/rest/vehicles</code>
and request body:  
```
{
    "brand": "Citroën",
    "registrationNumber": "888 8888",
    "type": "hatchback",
    "color": "white",
    "mileage": 111111
}
```

To update a vehicle use:  
<code>PUT localhost:8080/pa165/rest/vehicles</code>
and request body:  
```
{
    "id": 1,
    "brand": "Citroën",
    "registrationNumber": "999 9999",
    "type": "hatchback",
    "color": "white",
    "mileage": 111111
}
```

To delete a vehicle with specified id use:  
<code>DELETE localhost:8080/pa165/rest/vehicles/{id}</code>

#### Reservations
To get all the reservations use:  
<code>localhost:8080/pa165/rest/reservations</code>

To get a reservation with specified id use:  
<code>GET localhost:8080/pa165/rest/reservations/{id}</code>

To get reservations for specified employee use:  
<code>GET localhost:8080/pa165/rest/reservations/find-by-employee</code>
and request body:  
```
{
    "id": 1,
    "firstName": "Jmeno",
    "lastName": "Prijmeni",
    "username": "mojejmeno",
    "email": "jmeno@test.cz",
    "password": "test",    
    "position": "tester"
}
```

To get reservations for specified vehicle use:  
<code>GET localhost:8080/pa165/rest/reservations/find-by-vehicle</code>
and request body:  
```
{
    "id": 1,
    "brand": "Citroën",
    "registrationNumber": "999 9999",
    "type": "hatchback",
    "color": "white",
    "mileage": 111111
}
```

To delete a reservation with specified id use:  
<code>DELETE localhost:8080/pa165/rest/reservations/{id}</code>

#### ReservationSettings
To get all the reservation settings use:  
<code>localhost:8080/pa165/rest/reservation-settings</code>

To get a reservation setting with specified id use:  
<code>GET localhost:8080/pa165/rest/reservation-settings/{id}</code>

To get a reservation setting for specified employee use:  
<code>GET localhost:8080/pa165/rest/reservation-settings/find-by-employee</code>
and request body:  
```
{
    "id": 1,
    "firstName": "Jmeno",
    "lastName": "Prijmeni",
    "username": "mojejmeno",
    "email": "jmeno@test.cz",
    "password": "test",    
    "position": "tester"
}
```

To add a reservation setting use:  
<code>POST localhost:8080/pa165/rest/reservation-settings</code>
and request body:  
```
{
    "employee": {
        "id": 1,
        "firstName": "Jmeno",
        "lastName": "Prijmeni",
        "username": "mojejmeno",
        "email": "jmeno@test.cz",
        "password": "test",    
        "position": "tester"
    },
    "allowed": true,
    "autoApproval": false
}
```

To update a reservation setting use:  
<code>PUT localhost:8080/pa165/rest/reservation-settings</code>
and request body:  
```
{
    "id": 1,
    "employee": {
        "id": 1,
        "firstName": "Jmeno",
        "lastName": "Prijmeni",
        "username": "mojejmeno",
        "email": "jmeno@test.cz",
        "password": "test",    
        "position": "tester"
    },
    "allowed": true,
    "autoApproval": true
}
```

To delete a reservation setting with specified id use:  
<code>DELETE localhost:8080/pa165/rest/reservation-settings/{id}</code>

#### Login
To log in use:  
<code>POST localhost:8080/pa165/rest/login?username={username}&password={password}</code>

### Collaborators:
Polešovský, T.;
Applová, J.;
Svoreň, O.

*We use pull requests for our changes on the project. Tomas Polesovsky is our team lead and also a reviewer of the requests.*

##### Masaryk University, PA165 Project

## Car Park
### Official assignment from IS:
The project should emulate a real situation in a company. The company employees can apply for rental of a car. The rental is applied to a given date. Based on such application, the system will offer list of free cars and the system allows reservation of a car.

### Run the application with:
<code>mvn clean install && cd carpark-web && mvn cargo:run</code>

Our application is then available on
<code>http://localhost:8080/pa165</code>.

### REST API:
To log in see Login section. You won't be able to see any data without logging in.

#### Login
To log in use:  
<code>POST localhost:8080/pa165/rest/login</code>
and request body:  
```
{
    "username": "myusername",
    "password": "mypassword"
}
```

In the response body you will get a token. You need to use this Bearer Token to authorize when making any other rest api calls.

There are different roles in the system - you can be an approver or an average employee. If you log in with admin/admin you will get access to the approver's view. To view CarPark as a regular employee you can use e.g. jana/jana (or you create your own account or you can also see the sample data module for other logins). Every person in the company has their own account.

Approver can manage the whole application and its main job is to approve/decline not automatically handled reservation requests. Regular users can create their own reservations and list all the employees, vehicles and reservations. 

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
<code>GET localhost:8080/pa165/rest/vehicles</code>

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
<code>GET localhost:8080/pa165/rest/reservations</code>

To get a reservation with specified id use:  
<code>GET localhost:8080/pa165/rest/reservations/{id}</code>

To get reservations for specified employee use:  
<code>POST localhost:8080/pa165/rest/reservations/find-by-employee</code>
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
<code>POST localhost:8080/pa165/rest/reservations/find-by-vehicle</code>
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

To add a new reservation request use:  
<code>POST localhost:8080/pa165/rest/reservations/process-request</code>
and request body:  
```
{
    "from": "2019-03-18T07:45:00",
    "to": "2019-03-20T19:45:00",
    "employee": {
        "id": 1,
        "first_name": "Jmeno",
        "last_name": "Prijmeni",
        "username": "mojejmeno",
        "email": "jmeno@test.cz",
        "password": "test",
        "position": "tester"
    },
    "vehicle": {
        "id": 1,
        "brand": "BMW",
        "registration_number": "4A2 3000",
        "type": "sedan",
        "color": "red",
        "mileage": 200000
    },
    "status": "NEW"
}
```

The status of the reservation might get changed automatically depending on the settings for the selected employee.

To accept or decline a reservation request use:  
<code>PUT localhost:8080/pa165/rest/reservations/accept-or-decline?toBeAccepted={toBeAccepted}</code>
and request body:  
```
{
    "id": 1,
    "from": "2019-01-18T07:45:00",
    "to": "2019-01-20T19:45:00",
    "employee": {
        "id": 1,
        "first_name": "Jmeno",
        "last_name": "Prijmeni",
        "username": "mojejmeno",
        "email": "jmeno@test.cz",
        "password": "test",
        "position": "tester"
    },
    "vehicle": {
        "id": 1,
        "brand": "BMW",
        "registration_number": "4A2 3000",
        "type": "sedan",
        "color": "red",
        "mileage": 200000
    },
    "status": "NEW"
}
```

To update a reservation use:  
<code>PUT localhost:8080/pa165/rest/reservations</code>
and request body:  
```
{
    "id": 1,
    "from": "2019-01-18T07:45:00",
    "to": "2019-01-20T19:45:00",
    "employee": {
        "id": 1,
        "first_name": "Jmeno",
        "last_name": "Prijmeni",
        "username": "mojejmeno",
        "email": "jmeno@test.cz",
        "password": "test",
        "position": "tester"
    },
    "vehicle": {
        "id": 1,
        "brand": "BMW",
        "registration_number": "4A2 3000",
        "type": "sedan",
        "color": "red",
        "mileage": 200000
    },
    "status": "NEW"
}
```

To delete a reservation with specified id use:  
<code>DELETE localhost:8080/pa165/rest/reservations/{id}</code>

To filter reservations use:  
<code>POST localhost:8080/pa165/rest/reservations/filter</code>
and request body:  
```
{
	"page": "page"
	"page_size": "pagesize"
	"query": "query"
	"from": "from"
	"to": "to"
}
```

#### ReservationSettings
To get all the reservation settings use:  
<code>GET localhost:8080/pa165/rest/reservation-settings</code>

To get a reservation setting with specified id use:  
<code>GET localhost:8080/pa165/rest/reservation-settings/{id}</code>

To get a reservation setting for specified employee use:  
<code>POST localhost:8080/pa165/rest/reservation-settings/find-by-employee</code>
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

### Collaborators:
Polešovský, T.;
Applová, J.;
Svoreň, O.

*We use pull requests for our changes on the project. Tomas Polesovsky is our team lead and also a reviewer of the requests.*

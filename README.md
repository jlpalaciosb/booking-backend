# Booking Backend

This is the backend of an appointment booking system, as such, it provides a JSON API.

It was developed mainly with **Spring Boot 2** and Java 8.

The system defines services, clients, professionals and appointments.

# Demo

Please follow this link [https://bookin-backen.herokuapp.com/swagger-ui.html](https://bookin-backen.herokuapp.com/swagger-ui.html) to try out the API and check its documentation.

**Authentication**

On the Swagger page you will find the `Authentication Controller` which we use to get the token used to authorize requests.

You can use this credentials:
```bash
username: admin
password: admin
```

Then click on the `Authorize` button and place the token in the `value` field as the example given below:
 
```bash
Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwic2NvcGVzIjoiUk9MRV9BRE1JTiIsImlhdCI6MTU0NzE0NDg5MiwiZXhwIjoxNTUyMTkyODkyfQ.IVDrqPj_CmOQnK5VDRSALPKnYMraKpBGXDDoPwNbhkGInw0v0Kki4sK7D5xbN3U03iVBsFJj1SbwMnMkISibMQ
```

And now you can execute the requests on the API.

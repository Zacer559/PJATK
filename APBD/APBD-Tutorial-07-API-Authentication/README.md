# APBD-Tutorial-07-API-Authentication

## In Postman folder you will find ready to use requests.
## In Database.sql file you will find ready to use database queries.

#### Full documentation is accessible after running the program at:  ``` http://localhost:XXXXX/swagger ```

## Some Example usage:

### Login:
1. Get method
2. Adress ``` http://localhost:XXXXX/api/enrollment/login ```
3. Authorization: No authorization
4. Body

```
{
	"Login": "s555",
	"Password": "Pass"
}
```
5. Send


### Refresh Token:
1. Post method
2. Adress ``` http://localhost:XXXXX/api/enrollment/refresh/{refreshToken} ```
3. Authorization: No authorization
4. Body empty
5. Send





### Promote:
1. Post method
2. Adress ``` http://localhost:XXXXX/api/enrollment/promotion ```
3. Authorization: Bearer Token + Login Token obtained from Login.
4. Body

```
{
    "Study": "Art",
    "Semester" : 1
}
```

5. Send


### Enroll Student:
1. Post method
2. Adress ``` http://localhost:XXXXX/api/enrollment ```
3. Authorization: Bearer Token + Login Token obtained from Login.
4. Body
```
{
    "Name": "Franek",
    "Study": "IT",
    "Surname": "Kimono",
    "IndexNumber": "s566",
    "BirthDate": "2020-10-20"
}
```

5. Send

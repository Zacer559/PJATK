# APBD-Tutorial-05-API-Separated_Layer

## Example usage:

### Promote:
1. Post method
2. Adress ``` http://localhost:XXXXX/api/enrollment/promotion ```
3. Body

```
{
    "Study": "Art",
    "Semester" : 1
}
```

4. Send


### Enroll Student:
1. Post method
2. Adress ``` http://localhost:XXXXX/api/enrollment ```
3. Body
```
{
    "Name": "Franek",
    "Study": "IT",
    "Surname": "Kimono",
    "IndexNumber": "s566",
    "BirthDate": "2020-10-20"
}
```

4. Send

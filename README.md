# Eclipselink PrePersist Test

Project to reproduce the eclipseLink issue - ***Entity Attribute set in @PrePersist is not used***

Entity model:

```
User        -–has----->   UserAddress
User        -–refers-->   Tenant
UserAddress -–refers-->   Tenant
```

Steps to run the project:

1. Setup DB: ```src/main/resources/DB_create_scripts.sql```
2. Set DB persistence config in persistence.xml: ```src\main\resources```
3. Run  main class ELTestMain.java

Notes:
- Tenant 1 already exists
- User 1 already exists without UserAddress
- Trying to add UserAddress to User
- UserAddress->Tenant is set in @PrePersist method [in UserAddress.java @PrePersist (line 99)]

Issue:
- Tenant set in UserAddress @PrePersist is not considered and throws Exception

Strangely:
- In ELTTestMain.java if lines 38, 39 are commented, exception is seen. With these two lines commented, it works


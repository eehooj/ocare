# 고객 건강 활동 정보

## SKILL
```
* Spring boot 3.4
* Gradle
* JAVA 17
* Spring Data JPA & QueryDsl
* MySql 8.0
* Redis
```

## 패키지 구조

```
 src
   └─ main
      ├─ java
      │  └─ com
      │     └─ example
      │        └─ ocare
      │           ├─ OcareApplication.java
      │           ├─ api
      │           │  └─ health
      │           │     ├─ controller
      │           │     ├─ domain
      │           │     │  ├─ entity
      │           │     │  └─ repository
      │           │     │     └─ custom
      │           │     ├─ dto
      │           │     │  └─ response
      │           │     └─ service
      │           └─ global
      │              ├─ advice
      │              ├─ common
      │              │  ├─ dto
      │              │  ├─ entity
      │              │  └─ excel
      │              ├─ config
      │              ├─ exception
      │              │  └─ enumerate
      │              └─ util
      └─ resources
         ├─ application.yml
         └─ input
# Schedule API

## 일정 생성, 조회, 수정, 삭제 프로그램
Spring Boot 기반 CRUD(Create, Read, Update, Delete) 기능이 포함된 REST API

---

## Use Case Diagram
![스크린샷 2024-01-23 105432](https://github.com/wooseok50/Schedule-manage/assets/155416976/832dde18-e7db-4c7d-8612-cc8bbfc4c46c)

---
## API 명세서
![스크린샷 2024-01-23 105408](https://github.com/wooseok50/Schedule-manage/assets/155416976/7f930c38-60bb-4ec0-88fa-cca9ae555bab)

---
## ERD
![스크린샷 2024-01-23 105538](https://github.com/wooseok50/Schedule-manage/assets/155416976/cb196dbd-6fc9-4b65-8954-611eb2d60a9b)

---

## 비밀번호 확인
```
if (schedule.getPassword() != null && schedule.getPassword().equals(requestDto.getPassword())) {
  ... };
```


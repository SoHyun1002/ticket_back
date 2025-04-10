
# 📑 API Documentation

---

## 🚀 TEST 가이드 라인

API 테스트를 위한 기본 가이드입니다. 아래와 같은 형식으로 **JSON 데이터를 POST 요청**하여 **키 값을 받아올 수 있습니다.** (Postman 이용 권장)

---

### 📌 POST 요청 형식 
**URL:**  
```
POST http://localhost:8787/reservation/select
```

**Request Body (예시):**
```json
{
    "uName": "홍길동",
    "uId": 1001,
    "pId": 101,
    "pTitle": "뮤지컬 - 햄릿",
    "pPlace": "서울 예술의 전당",
    "pDate": "2025-05-10",
    "pPrice": "50000",
    "pAllSpot": 20,
    "rId": 2001,
    "rPhone": "010-1234-5678",
    "rEmail": "hong@test.com",
    "rTime": "2025-04-08T16:08:37"
}
```

---

### 🔑 키 값 사용법

1. **키 값을 성공적으로 생성받았다면:**  
   - 다음과 같은 URL로 접속하여 좌석을 선택하고 예매하기 버튼을 클릭하세요.  
   ```
   http://localhost:3000/select/키값대입
   ```
   - 선택 완료 후 예매 확인 페이지로 이동합니다.

---

### 📝 예매 정보 확인

예매 확인 페이지에서 **예매 확인 버튼**을 클릭한 후, 다시 Postman으로 돌아와 아래와 같은 **GET 요청**을 수행하여 예약 정보를 확인할 수 있습니다.

**GET 요청 형식:**  
```
GET http://localhost:8787/reservation/seat/status?key=키값대입
```

**확인 가능한 데이터:**  
- 선택한 좌석 정보가 뜨면 성공적

---

### 💡 TIP:

- **Postman 사용 방법:**  
  - **POST 요청:** 위의 예시 JSON을 **Body > raw > JSON** 형식으로 설정하여 전송합니다.  
  - **GET 요청:** `key` 값을 URL에 직접 대입하여 전송합니다.
  - 

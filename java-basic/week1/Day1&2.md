[폴더 구조]

src/com/example/studyroom
- Main.java
- domain
    - Room.java
    - Reservation.java
    - RoomType.java
    - MembershipGrade.java
    - ReservationStatus.java
- service
    - ReservationService.java
    - ReservationValidator.java
    - PriceCalculator.java
    - TimeSlotGenerator.java

[핵심 클래스 코드]

ReservationService의 reserve() 메서드가
1. 예약 검증
2. 가격 계산
3. 예약 완료
   흐름을 담당하도록 구성했다.

[내가 나눈 책임 설명]

- Room, Reservation은 예약 도메인 데이터를 표현한다.
- ReservationValidator는 예약 가능 여부 검증 책임을 가진다.
- PriceCalculator는 가격 계산 책임을 가진다.
- TimeSlotGenerator는 예약 가능 슬롯 생성 책임을 가진다.
- ReservationService는 여러 객체를 협력시켜 예약 흐름을 조합한다.
- Main은 객체를 생성하고 서비스 호출만 담당한다.

[막힌 부분]

- 생성자와 getter를 직접 만들어 객체를 생성하는 방식이 처음엔 익숙하지 않았다.
- Reservation 객체에서 값을 꺼내 service로 넘기고, service가 validator/calculator에 다시 전달하는 흐름이 가장 어려웠다.
- validate 로직을 객체 기반으로 바꾸는 과정에서 기존 문자열 중심 방식과 충돌이 있었다.

[AI 사용 내용]

- Day1 기능 코드를 Day2 객체지향 구조로 재배치하는 방법
- ReservationService에 흐름을 모으는 방식
- Room, Reservation 객체를 main과 service 흐름에 연결하는 방법
- validate 메서드를 Day2 수준에 맞게 구현하는 방향

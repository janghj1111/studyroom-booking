# Week1 Day3

## 목표
- Day2에서 만든 객체 협력 구조를 바탕으로 예외 처리 구조를 도입한다.
- 검증 실패 결과를 enum 반환에서 사용자 정의 예외로 확장한다.
- 가격 계산 규칙을 인터페이스로 분리할 준비를 한다.
- 전략 패턴의 아주 기초 형태를 맛보는 것을 목표로 한다.

## 왜 Day3를 하는가
- Day2는 구조를 만드는 날이었다면, Day3는 실패와 정책을 분리하는 날이다.
- 실제 서비스 코드는 성공 흐름만큼 실패 흐름을 어떻게 표현하느냐가 중요하다.
- 가격 정책과 검증 정책이 늘어날수록 if문을 늘리는 방식은 한계가 있으므로, 미리 분리 방향을 익혀두는 것이 중요하다.

## 해야 할 일
1. `ReservationStatus`를 언제 유지하고 언제 예외로 바꿀지 기준을 정리한다.
2. `ReservationValidator`에서 검증 실패 상황별로 사용자 정의 예외를 설계한다.
3. 예외 클래스 후보를 만든다.
4. `ReservationService`가 검증 실패를 예외 흐름으로 처리하도록 구조를 바꿔본다.
5. `PriceCalculator`의 규칙을 인터페이스 기반으로 분리할 수 있는지 초안을 잡는다.

## 예외 클래스 초안
- `DuplicateReservationException`
- `OutOfBusinessHoursException`
- `PastTimeReservationException`

## 인터페이스 분리 초안
- `PricePolicy`
- `WeekendPricePolicy`
- `VipDiscountPolicy`
- `LongStayDiscountPolicy`

## Day3 핵심 질문
- 검증 실패를 왜 enum 대신 예외로 표현하려는가
- `ReservationService`는 예외를 직접 처리해야 하는가, 아니면 위로 던져야 하는가
- 가격 계산 규칙이 많아질 때 어떤 구조가 변경에 더 유리한가
- 정책을 분리하면 어떤 클래스의 책임이 더 선명해지는가

## 완료 기준
- 검증 실패 상황을 예외로 표현하는 구조를 설명할 수 있다.
- `ReservationValidator`와 `ReservationService`의 책임 차이를 더 명확히 설명할 수 있다.
- 가격 계산 규칙을 인터페이스로 분리해야 하는 이유를 말할 수 있다.
- Day4 이상으로 확장 가능한 구조를 그릴 수 있다.

## 작업 후 기록할 것
- 오늘 배운 점
- 구현하면서 막힌 부분
- 구조적으로 좋아진 점
- 아직 어색한 부분
- 다음 Day에 넘길 숙제

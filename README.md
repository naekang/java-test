# JUnit5 알아보기
---

1. 테스트 이름 표기하는 방법 - `@DisplayName`
2. JUnit5, jupiter의 세가지 모듈 중 테스트를 실행하는 런처와 테스트 엔진의 API를 제공하는 모듈 - `junit platform`
3. JUnit5에서 테스트 그룹을 만들고 필터링하여 실행하는데 사용하는 어노테이션 - `@Tag`
4. JUnit 5가 제공하는 확장팩 등록 방법
   - `@ExtendWith`: 선언적 등록
   - `@RegisterExtension`: 코드를 통해 제공
   - `ServiceLoader`: 자동으로 등록

# Mockito 사용
- 레퍼런스: https://javadoc.io/doc/org.mockito/mockito-core/latest/index.html
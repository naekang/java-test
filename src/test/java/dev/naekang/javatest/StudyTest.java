package dev.naekang.javatest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.time.Duration;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    // 스터디를 처음 만들면 상태는 DRAFT 상태이어야 한다.
    @Test
    @DisplayName("스터디 만들기 \uD83D\uDE31")
    @EnabledOnOs({OS.MAC, OS.LINUX})
    void create_new_study() {

        String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);
        assertTrue("LOCAL".equalsIgnoreCase(test_env));
        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);


//        assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
//            Study actual = new Study(100);
//            assertThat(actual.getLimit()).isGreaterThan(0);
//        });
//
//        assumingThat("jinho".equalsIgnoreCase(test_env), () -> {
//            Study actual = new Study(10);
//            assertThat(actual.getLimit()).isGreaterThan(0);
//        });

        // asserJ 사용하기
//        Study actual = new Study(10);
//        assertThat(actual.getLimit()).isGreaterThan(0);

        // 특정 시간 안에 실행이 완료되는 지 확인
//        assertTimeout(Duration.ofMillis(100), () -> {
//            new Study(10);
//            Thread.sleep(300);
//        });

        // 예외 발생 확인
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
//
//        assertEquals("limit은 0보다 커야한다.", exception.getMessage());

//        Study study = new Study(-10);

        // 모든 구문 확인
//        assertAll(
//                () -> assertNotNull(study),
//                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DRAFT 이어야 한다."),
//                () -> assertTrue(study.getLimit() > 0, () -> "스터디 최대 참석 가능 인원은 0보다 커야 한다.")
//        );
    }

    @Test
    @DisplayName("스터디 만들기 ><")
    void create_new_study_again() {
        System.out.println("create1");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }

}
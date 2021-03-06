package dev.naekang.javatest.study;

import dev.naekang.javatest.domain.Member;
import dev.naekang.javatest.domain.Study;
import dev.naekang.javatest.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@Testcontainers
@Slf4j
@ContextConfiguration(initializers = StudyServiceTest.ContainerPropertyInitializer.class)
class StudyServiceTest {

    @Mock
    MemberService memberService;

    @Autowired
    StudyRepository studyRepository;

    @Value("${container.port}") int port;

    @Container
    static DockerComposeContainer composeContainer = new DockerComposeContainer(new File("src/test/resources/docker-compose.yml"))
            .withExposedService("study-db", 5432);

    @Test
    void createNewStudy() {

        System.out.println("=============");
        System.out.println(port);

        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("rlawlsgh6306@gmail.com");

        Study study = new Study(10, "?????????");

        given(memberService.findById(1L)).willReturn(Optional.of(member));

        // When
        studyService.createNewStudy(1L, study);

        // Then
        assertEquals(1L, study.getOwnerId());
        then(memberService).should(times(1)).notify(study);
        then(memberService).shouldHaveNoMoreInteractions();
    }

    @Test
    @DisplayName("?????? ???????????? ??? ??? ????????? ???????????? ????????????.")
    void openStudy() {
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "??? ??????, ?????????");
        assertNull(study.getOpenedDateTime());

        // TODO studyRepository Mock ????????? save ?????????????????? ??? study??? ??????????????? ?????????.

        // When
        studyService.openStudy(study);

        // Then
        // TODO study??? status??? OPENED??? ??????????????? ??????
        assertEquals(StudyStatus.OPENED, study.getStatus());

        // TODO study??? openedDataTime??? null??? ????????? ??????
        assertNotNull(study.getOpenedDateTime());

        // TODO memberService??? notify(study)??? ?????? ????????? ??????.
        then(memberService).should().notify(study);

    }

    static class ContainerPropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of("container.port=" + composeContainer.getServicePort("study-db", 5432))
                    .applyTo(applicationContext.getEnvironment());
        }
    }
}
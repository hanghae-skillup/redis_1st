package hellojpa.service;

import hellojpa.dto.ReservationCompletedMessageDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @InjectMocks
    @Spy
    private MessageService messageService;

    private ReservationCompletedMessageDto eventDto;

    @BeforeEach
    void setUp() {
        eventDto = new ReservationCompletedMessageDto(1L, "테스트 메시지");
    }

    @Test
    @DisplayName("예약 완료 이벤트 처리 - 정상 케이스")
    void handleReservationCompletedEvent_Success() throws Exception {
        // when
        messageService.handleReservationCompletedEvent(eventDto);

        // then
        verify(messageService, timeout(1000).times(1)).sendMessageAsync(eventDto);
    }

    @Test
    @DisplayName("예약 완료 이벤트 처리 - null 이벤트")
    void handleReservationCompletedEvent_NullEvent() {
        // when & then
        assertThrows(IllegalArgumentException.class,
                () -> messageService.handleReservationCompletedEvent(null));
    }

    @Test
    @DisplayName("예약 완료 이벤트 처리 - 비동기 실행 확인")
    void handleReservationCompletedEvent_AsyncExecution() throws Exception {
        // given
        AtomicBoolean methodCalled = new AtomicBoolean(false);
        ReservationCompletedMessageDto testDto = new ReservationCompletedMessageDto(2L, "비동기 테스트 메시지");

        doAnswer(invocation -> {
            methodCalled.set(true);
            return null;
        }).when(messageService).sendMessageAsync(any());

        // when
        messageService.handleReservationCompletedEvent(testDto);

        // then
        // 비동기 처리가 호출되었는지 확인
        verify(messageService, timeout(2000)).sendMessageAsync(any());
        Thread.sleep(1000); // 비동기 작업 완료 대기
        assertTrue(methodCalled.get());
    }

    @Test
    @DisplayName("메시지 전송 중 인터럽트 발생 시나리오")
    void handleReservationCompletedEvent_WithInterrupt() throws Exception {
        // given
        doAnswer(invocation -> {
            Thread.sleep(500);
            return null;
        }).when(messageService).sendMessageAsync(any());

        // when
        Thread testThread = new Thread(() ->
                messageService.handleReservationCompletedEvent(eventDto));
        testThread.start();
        testThread.interrupt();

        // then
        testThread.join(1000);
        assertFalse(testThread.isAlive());
    }
}
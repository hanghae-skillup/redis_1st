package hellojpa.publisher;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventPublisherTest {

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private EventPublisher eventPublisher;

    @Test
    void testPublishEvent() {
        // Given
        Object event = new Object(); // 실제로 발행할 이벤트 객체

        // When
        eventPublisher.publish(event);

        // Then
        // ApplicationEventPublisher의 publishEvent 메서드가 호출되었는지 확인
        verify(applicationEventPublisher).publishEvent(event);
    }
}
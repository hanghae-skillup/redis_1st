package fcm;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FcmService {

	public void sendMessage() throws InterruptedException {
		Thread.sleep(500);
		log.info("앱 푸시 발송");
	}

}

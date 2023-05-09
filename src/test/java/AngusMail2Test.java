import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.integration.mail.ImapMailReceiver;

import com.icegreen.greenmail.junit5.GreenMailExtension;
import com.icegreen.greenmail.util.ServerSetupTest;

class AngusMail2Test {
	@RegisterExtension
	static GreenMailExtension greenMailExtension = new GreenMailExtension(ServerSetupTest.ALL);

	@Test
	void waitForNewMessages_should_not_throw_anyException() throws Exception {
		greenMailExtension.setUser("bar", "pw");

		final ImapMailReceiver imapMailReceiver = new ImapMailReceiver("imap://bar:pw@localhost:" + greenMailExtension.getImap().getPort() + "/INBOX");
		imapMailReceiver.afterPropertiesSet();
		imapMailReceiver.setCancelIdleInterval(1);

		assertDoesNotThrow(() ->
			imapMailReceiver.waitForNewMessages()
		);
	}
}

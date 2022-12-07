package Utilities;

import db.entities.VerificationLinkEntity;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MailerUtility {

    @Inject
    Mailer mailer;

    public void sendVerificationEmail(VerificationLinkEntity entity) {
        String verificationCode = entity.key;
//        mailer.send(Mail.withHtml(
//
//        ));
    }
}

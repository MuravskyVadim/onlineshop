package service.interfaces;

import utils.Code;

public interface MailService {

    void sendConfirmCode(Code code);
}

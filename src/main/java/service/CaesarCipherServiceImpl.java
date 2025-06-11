package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Сервис вычисления шифра Цезаря
public class CaesarCipherServiceImpl implements CaesarCipherService {
    private final Logger logger = LoggerFactory.getLogger(CaesarCipherServiceImpl.class);
    private final CaesarCipherComponent component;

    public CaesarCipherServiceImpl(CaesarCipherComponent component) {
        this.component = component;
    }
    //шифрование выражения
    @Override
    public String encryptText(String userText, int shift) {
        logger.debug("Encrypting user text: {}", userText);
        String encryptedUserText = component.realize(userText, shift);
        logger.debug("User text was encrypted");
        return encryptedUserText;
    }
    //расшифровка выражения
    @Override
    public String decryptText(String userText, int shift) {
        logger.debug("Decrypting user text: {}", userText);
        String decryptedUserText = component.realize(userText, -shift);
        logger.debug("User text was decrypted");
        return decryptedUserText;
    }
}


package service;

public interface CaesarCipherService {

    public String encryptText(String userText, int shift);

    public String decryptText(String userText, int shift);
}

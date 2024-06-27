import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

public class FileSigning {
    public static void main(String[] args) throws Exception{
        //loading the private key
        byte[] privateKeyByte = Files.readAllBytes(Paths.get("private_key.der"));
        PKCS8EncodedKeySpec a = new PKCS8EncodedKeySpec(privateKeyByte);
        PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(a);
        // loading the file to be signed
        byte[] inputFile = Files.readAllBytes(Paths.get("name.docx"));
        // singing the file
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(inputFile);
        byte[] DigitalSignature = signature.sign();
        // storing the digital signature in a file
        Files.write(Paths.get("DigitalSignature"),DigitalSignature);
    }
}

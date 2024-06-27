import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

public class VerifyingSignature {
    public static void main(String[] args) throws Exception {
        // loading the public key
        byte[] publicKeyByte = Files.readAllBytes(Paths.get("public_key.der"));
        X509EncodedKeySpec b = new X509EncodedKeySpec(publicKeyByte);
        PublicKey publickey = KeyFactory.getInstance("RSA").generatePublic(b);
        // load DigitalSignature
        byte[] digitalSignature = Files.readAllBytes(Paths.get("DigitalSignature"));
        // load file
        byte[] inputFile = Files.readAllBytes(Paths.get("name.docx"));
        // verifying the File
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publickey);
        signature.update(inputFile);
        Boolean verified = signature.verify(digitalSignature);
        if(verified){
            System.out.println("File is not tempered");
        }
        else System.out.println("file is tempered");

    }
}

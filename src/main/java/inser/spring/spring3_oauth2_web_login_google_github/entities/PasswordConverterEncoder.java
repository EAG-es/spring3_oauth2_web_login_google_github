package inser.spring.spring3_oauth2_web_login_google_github.entities;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Converter class to Encode data before storing into Database and Decode while retrieving data from Database
 */
@Converter
public class PasswordConverterEncoder implements AttributeConverter<String, String>, PasswordEncoder {
	
//	// Secrete Key for encryption
//	final String SECRETE_KEY = "Spring3_oauth2_simple";
//	// Encryption algorithm
//	final String ENC = "AES";
//	final String ALGORITHM = "AES/ECB/PKCS5Padding";
//
//	private final Logger logger = LoggerFactory.getLogger(getClass());
//
//	@Override
//	public String convertToDatabaseColumn(String attribute) {
//		// Encode data to store into database
//		logger.info("Convert Application data to Database: {}", attribute);
//		String value = null;
//		try {
//			Key key = new SecretKeySpec(SECRETE_KEY.getBytes(), ENC);
//			Cipher c = Cipher.getInstance(ALGORITHM);
//			c.init(Cipher.ENCRYPT_MODE, key);
//			value = Base64.getEncoder().encodeToString(c.doFinal(attribute.getBytes()));
//
//		} catch(Exception e) {
//			logger.info("Failed to encode: "+ e.getMessage());
//			e.printStackTrace();
//		}
//		return value;
//	}
//
//	@Override
//	public String convertToEntityAttribute(String dbData) {
//		// Decode data to use in Application
//		logger.info("Convert Datbase to Application data: {}", dbData);
//		String value = null;
//		try {
//			Key key = new SecretKeySpec(SECRETE_KEY.getBytes(), ENC);
//			Cipher c = Cipher.getInstance(ALGORITHM);
//			c.init(Cipher.DECRYPT_MODE, key);
//			value = new String(c.doFinal(Base64.getDecoder().decode(dbData)));
//
//		} catch(Exception e) {
//			logger.info("Failed to decode: "+ e.getMessage());
//			e.printStackTrace();
//		}
//		return value;
//	}

	public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public String convertToDatabaseColumn(String attribute) {
		String password_encoded = passwordEncoder.encode(attribute);
		return password_encoded;
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		return dbData;
	}

	@Override
	public String encode(CharSequence rawPassword) {
		String password = rawPassword.toString();
		password = convertToDatabaseColumn(password);
		return password;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String password = rawPassword.toString();
//				password = passwordConverter.convertToDatabaseColumn(password);
//				return Objects.equals(password, encodedPassword);
// 				Get some encoded passwords:
//				encodedPassword = passwordConverter.passwordEncoder.encode("manager123");
//				encodedPassword = passwordConverter.passwordEncoder.encode("staff123");
//				encodedPassword = passwordConverter.passwordEncoder.encode("admin123");
		return passwordEncoder.matches(password, encodedPassword);
	}

}

package BW_U5.EPIC_ENERGY_SERVICES.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
	@Value("${cloudinary.apikey}")
	private String apiKey;
	@Value("${cloudinary.secret}")
	private String apiSecret;
	@Value("${cloudinary.name}")
	private String cloudinaryName;

	@Bean
	public Cloudinary cloudinary() {
		Map<String, String> config = new HashMap<>();
		config.put("cloudinary.apikey", apiKey);
		config.put("cloudinary.secret", apiSecret);
		config.put("cloudinary.name", cloudinaryName);
		return new Cloudinary(config);
	}
}

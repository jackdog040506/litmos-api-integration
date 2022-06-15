package test.io.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties("litmos")
@Data
public class LitmosConfig {
	private String url;
	private String apiKey;
	private String source = "default";
}

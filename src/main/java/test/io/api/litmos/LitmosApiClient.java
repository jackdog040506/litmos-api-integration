package test.io.api.litmos;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import test.io.api.litmos.request.IdWrapper;
import test.io.api.litmos.response.TeamsInfo;
import test.io.api.litmos.response.UserInfoWithCourses;
import test.io.config.LitmosConfig;

@Component
@Slf4j
public class LitmosApiClient {

	private LitmosUserApi userApi;
	private LitmosCourseApi courseApi;
//	private LitmosConfig litmosConfig;

	public LitmosApiClient(LitmosConfig litmosConfig) {
//		this.litmosConfig = litmosConfig;
		OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

		okHttpClientBuilder.addInterceptor(new Interceptor() {
			@Override
			public Response intercept(Chain chain) throws IOException {
				Request request = chain
						.request()
							.newBuilder()
							.addHeader("apikey", litmosConfig.getApiKey())
							.addHeader("Content-Type", "application/json")
							.addHeader("Accept", "application/json")
							.build();
				return chain.proceed(request);
			}
		});
		HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
		httpLoggingInterceptor.setLevel(Level.BODY);
		okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);
		OkHttpClient httpClient = okHttpClientBuilder.build();

		ObjectMapper OM = new ObjectMapper();
		OM.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
		userApi = new Retrofit.Builder().baseUrl(litmosConfig.getUrl()).client(httpClient)
//				.addConverterFactory(JaxbConverterFactory.create())
					.addConverterFactory(ScalarsConverterFactory.create())
					.addConverterFactory(JacksonConverterFactory.create(OM))
					.build()
					.create(LitmosUserApi.class);
		courseApi = new Retrofit.Builder().baseUrl(litmosConfig.getUrl()).client(httpClient)
//				.addConverterFactory(JaxbConverterFactory.create())
					.addConverterFactory(ScalarsConverterFactory.create())
					.addConverterFactory(JacksonConverterFactory.create(OM))
					.build()
					.create(LitmosCourseApi.class);
	}

	public <T> T executeUser(Function<LitmosUserApi, Call<T>> func) {
		return execute(func.apply(userApi));
	}

	public <T> T executeCourse(Function<LitmosCourseApi, Call<T>> func) {
		return execute(func.apply(courseApi));
	}

	public <T> T execute(Call<T> supplier) {
		retrofit2.Response<T> response;
		try {
			response = supplier.execute();
			return response.body();
		} catch (IOException e) {
			log.error("execute", e);
			throw new RuntimeException(e);
		}
	}

	public List<UserInfoWithCourses> getUserCourseByUserIds(List<String> userIds, String source) {
		// Create FileWriter object.
		StringWriter sw = new StringWriter();
		try {
			// Get XMLOutputFactory instance.
			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(sw);
			xmlStreamWriter.writeStartElement("Users");
			for (String userId : userIds) {
				xmlStreamWriter.writeStartElement("User");
				xmlStreamWriter.writeStartElement("Id");
				xmlStreamWriter.writeCharacters(userId);
				xmlStreamWriter.writeEndElement();
				xmlStreamWriter.writeEndElement();
			}
			xmlStreamWriter.writeEndDocument();
			RequestBody requestBody = RequestBody.create(sw.toString(), okhttp3.MediaType.parse("text/xml"));
			return execute(courseApi.getUserCoursesByIds(requestBody, source));
		} catch (Exception e) {
			log.error("xml parsing error", e);
			return new ArrayList<>();
		}
	}

	public String assignCoursesToUser(String userId, List<String> courseIds, String source) {
		// Create FileWriter object.
//		StringWriter sw = new StringWriter();
		try {
//			// Get XMLOutputFactory instance.
//			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
//			XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(sw);
//			xmlStreamWriter.writeStartElement("Courses");
//			for (String courseId : courseIds) {
//				xmlStreamWriter.writeStartElement("Course");
//				xmlStreamWriter.writeStartElement("Id");
//				xmlStreamWriter.writeCharacters(courseId);
//				xmlStreamWriter.writeEndElement();
//				xmlStreamWriter.writeEndElement();
//			}
//			xmlStreamWriter.writeEndDocument();
//			RequestBody requestBody = RequestBody.create(sw.toString(), okhttp3.MediaType.parse("text/xml"));
			List<IdWrapper> ids = courseIds.stream().map(str -> {
				IdWrapper idWrapper = new IdWrapper();
				idWrapper.setId(str);
				return idWrapper;
			}).collect(Collectors.toList());
			return execute(courseApi.assignCoursesToUser(userId, ids, source));
		} catch (Exception e) {
			log.error("xml parsing error", e);
			return "error";
		}
	}

	public String assignLearningPathsToUser(String userId, List<String> learningPathIds, String source) {
		// Create FileWriter object.
		StringWriter sw = new StringWriter();
		try {
			// Get XMLOutputFactory instance.
			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(sw);
			xmlStreamWriter.writeStartElement("LearningPaths");
			for (String learningPathId : learningPathIds) {
				xmlStreamWriter.writeStartElement("LearningPath");
				xmlStreamWriter.writeStartElement("Id");
				xmlStreamWriter.writeCharacters(learningPathId);
				xmlStreamWriter.writeEndElement();
				xmlStreamWriter.writeEndElement();
			}
			xmlStreamWriter.writeEndDocument();
			RequestBody requestBody = RequestBody.create(sw.toString(), okhttp3.MediaType.parse("text/xml"));
			return execute(courseApi.assignLearningPathsToUser(userId, requestBody, source));
		} catch (Exception e) {
			log.error("xml parsing error", e);
			return "error";
		}
	}

	public String writeCustomFieldToUser(String userId, Map<String, String> parameters, String source) {

		// Create FileWriter object.
		StringWriter sw = new StringWriter();
		try {
			// Get XMLOutputFactory instance.
			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(sw);
			xmlStreamWriter.writeStartElement("UserCustomField");
			for (Entry<String, String> entry : parameters.entrySet()) {
				xmlStreamWriter.writeStartElement("Parameter");
				xmlStreamWriter.writeAttribute("fieldname", entry.getKey());
				xmlStreamWriter.writeCharacters(entry.getValue());
				xmlStreamWriter.writeEndElement();
			}
			xmlStreamWriter.writeEndDocument();
			RequestBody requestBody = RequestBody.create(sw.toString(), okhttp3.MediaType.parse("text/xml"));
			return execute(userApi.writeUserCustomFields(userId, requestBody, source));
		} catch (Exception e) {
			log.error("xml parsing error", e);
			return "";
		}
	}

	public List<IdWrapper> assignUserToTeams(String userId, List<String> teamsIds, String source) {

		// Create FileWriter object.
		StringWriter sw = new StringWriter();
		try {
			// Get XMLOutputFactory instance.
			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(sw);
			xmlStreamWriter.writeStartElement("Teams");
			for (String teamsId : teamsIds) {
				xmlStreamWriter.writeStartElement("Team");
				xmlStreamWriter.writeStartElement("Id");
				xmlStreamWriter.writeCharacters(teamsId);
				xmlStreamWriter.writeEndElement();
				xmlStreamWriter.writeEndElement();
			}
			xmlStreamWriter.writeEndDocument();
			RequestBody requestBody = RequestBody.create(sw.toString(), okhttp3.MediaType.parse("text/xml"));
			return execute(userApi.addTeamsToUser(userId, requestBody, source));
		} catch (Exception e) {
			log.error("xml parsing error", e);
			return new ArrayList<>();
		}
	}

	public TeamsInfo createTeams(TeamsInfo teamsInfo, String source) {
		// Create FileWriter object.
		StringWriter sw = new StringWriter();
		try {
			// Get XMLOutputFactory instance.
			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(sw);
			xmlStreamWriter.writeStartElement("Team");

			xmlStreamWriter.writeStartElement("Id");
			xmlStreamWriter.writeEndElement();

			xmlStreamWriter.writeStartElement("Name");
			xmlStreamWriter.writeCharacters(teamsInfo.getName());
			xmlStreamWriter.writeEndElement();

			xmlStreamWriter.writeStartElement("Description");
			xmlStreamWriter.writeCharacters(teamsInfo.getDescription());
			xmlStreamWriter.writeEndElement();

			xmlStreamWriter.writeStartElement("ParentTeamId");
			xmlStreamWriter.writeEndElement();

			xmlStreamWriter.writeEndElement();

			xmlStreamWriter.writeEndDocument();
			RequestBody requestBody = RequestBody.create(sw.toString(), okhttp3.MediaType.parse("text/xml"));
			return execute(userApi.createTeams(requestBody, source));
		} catch (Exception e) {
			log.error("xml parsing error", e);
			return null;
		}
	}

	public TeamsInfo createTeamsAsSubTeams(String parentTeamsId, TeamsInfo teamsInfo, String source) {
		// Create FileWriter object.
		StringWriter sw = new StringWriter();
		try {
			// Get XMLOutputFactory instance.
			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(sw);
			xmlStreamWriter.writeStartElement("Team");

			xmlStreamWriter.writeStartElement("Id");
			xmlStreamWriter.writeEndElement();

			xmlStreamWriter.writeStartElement("Name");
			xmlStreamWriter.writeCharacters(teamsInfo.getName());
			xmlStreamWriter.writeEndElement();

			xmlStreamWriter.writeStartElement("Description");
			xmlStreamWriter.writeCharacters(teamsInfo.getDescription());
			xmlStreamWriter.writeEndElement();

			xmlStreamWriter.writeStartElement("ParentTeamId");
			xmlStreamWriter.writeEndElement();

			xmlStreamWriter.writeEndDocument();
			RequestBody requestBody = RequestBody.create(sw.toString(), okhttp3.MediaType.parse("text/xml"));
			return execute(userApi.createTeamsAsSubTeams(parentTeamsId, requestBody, source));
		} catch (Exception e) {
			log.error("xml parsing error", e);
			return null;
		}
	}

	public String assignCoursesToTeams(String teamsId, List<String> courseIds, String source) {
		// Create FileWriter object.
		StringWriter sw = new StringWriter();
		try {
//			// Get XMLOutputFactory instance.
			XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(sw);
			xmlStreamWriter.writeStartElement("Courses");
			for (String courseId : courseIds) {
				xmlStreamWriter.writeStartElement("Course");
				xmlStreamWriter.writeStartElement("Id");
				xmlStreamWriter.writeCharacters(courseId);
				xmlStreamWriter.writeEndElement();
//				xmlStreamWriter.writeStartElement("CourseTeamLibrary");
//				xmlStreamWriter.writeCharacters("true/false");
//				xmlStreamWriter.writeEndElement();
				xmlStreamWriter.writeEndElement();
			}
			xmlStreamWriter.writeEndDocument();
			RequestBody requestBody = RequestBody.create(sw.toString(), okhttp3.MediaType.parse("text/xml"));
			return execute(courseApi.assignCoursesToTeams(teamsId, requestBody, source));
		} catch (Exception e) {
			log.error("xml parsing error", e);
			return "error";
		}
	}
}

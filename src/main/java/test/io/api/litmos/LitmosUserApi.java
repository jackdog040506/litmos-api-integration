package test.io.api.litmos;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import test.io.api.litmos.response.TeamsInfo;
import test.io.api.litmos.response.UserInfo;
import test.io.api.litmos.response.UserInfoAdvanced;

public interface LitmosUserApi {
	/**
	 * 
	 * @param source
	 * @return
	 */
	@GET("users")
	Call<List<UserInfo>> getAllUser(@Query("source") String source);

	/**
	 * @param source
	 * @param search
	 * @return
	 */
	@GET("users")
	Call<List<UserInfo>> getAllUser(@Query("source") String source, @Query("search") String search);

	/**
	 * Mandatory list of fields:
	 * <User>
	 * <Id>[Mandatory for PUT]</Id>
	 * <UserName>MyUserName</UserName>
	 * <FirstName>MyFirstName</FirstName>
	 * <LastName>MyLastName</LastName>
	 * <FullName></FullName>
	 * <Email>testing124@test.com</Email>
	 * <AccessLevel>Learner</AccessLevel>
	 * <DisableMessages>false</DisableMessages>
	 * <Active>true</Active>
	 * <LastLogin></LastLogin>
	 * <LoginKey></LoginKey>
	 * <IsCustomUsername>true</IsCustomUsername>
	 * <SkipFirstLogin>false</SkipFirstLogin>
	 * <TimeZone></TimeZone>
	 * </User>
	 * @param requestBody
	 * @param source
	 * @return
	 */
	@POST("users")
	Call<UserInfoAdvanced> createUser(@Body RequestBody requestBody,@Query("source") String source);
	
	/**
	 * 
	 * @param userId
	 * @param source
	 * @return
	 */
	@GET("users/{userId}")
	Call<UserInfoAdvanced> getUser(@Path("userId") String userId, @Query("source") String source);

	/**
	 * 
	 * @param userId
	 * @param source
	 * @return
	 */
	@GET("users/{userId}/usercustomfields")
	Call<String> getUserCustomFields(@Path("userId") String userId, @Query("source") String source);

	/**
	 * 
	 * @param userId
	 * @param customfieldWrapper XML body
	 * @param source
	 * @return
	 */
	@POST("users/{userId}/usercustomfields")
	Call<String> writeUserCustomFields(@Path("userId") String userId, @Body RequestBody userWrapper,
			@Query("source") String source);

	/**
	 * 
	 * @param userId
	 * @param source
	 * @return
	 */
	@GET("users/{userId}/teams")
	Call<List<TeamsInfo>> getUserTeams(@Path("userId") String userId, @Query("source") String source);

	/**
	 * 
	 * @param userId
	 * @param requestBody XML body
	 * @param source
	 * @return
	 */
	@POST("users/{userId}/teams")
	Call<String> addTeamsToUser(@Path("userId") String userId, @Body RequestBody requestBody,
			@Query("source") String source);

	/**
	 * 
	 * @param source
	 * @return
	 */
	@GET("teams")
	Call<List<TeamsInfo>> getAllTeams(@Query("source") String source);

	/**
	 * 
	 * @param teamId
	 * @param source
	 * @return
	 */
	@GET("teams/{teamsId}/teams")
	Call<List<TeamsInfo>> getSubTeams(@Path("teamsId") String teamId, @Query("source") String source);

	/**
	 * 
	 * @param teamId
	 * @param source
	 * @return
	 */
	@GET("teams/{teamsId}")
	Call<TeamsInfo> getTeamsById(@Path("teamsId") String teamId, @Query("source") String source);

	/**
	 * 
	 * @param teamId
	 * @param source
	 * @return
	 */
	@PUT("teams/{teamsId}")
	Call<TeamsInfo> updateTeamsById(@Path("teamsId") String teamsId, @Body RequestBody requestBody,
			@Query("source") String source);

	/**
	 * 
	 * @param requestBody XML body
	 * @param source
	 * @return
	 */
	@POST("teams")
	Call<TeamsInfo> createTeams(@Body RequestBody requestBody, @Query("source") String source);

	/**
	 * 
	 * @param teamsId
	 * @param requestBody XML body
	 * @param source
	 * @return
	 */
	@POST("teams/{teamsId}/teams")
	Call<TeamsInfo> createTeamsAsSubTeams(@Path("teamsId") String teamsId, @Body RequestBody requestBody,
			@Query("source") String source);

}

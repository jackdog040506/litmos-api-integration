package test.io.api.litmos;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import test.io.api.litmos.request.IdWrapper;
import test.io.api.litmos.response.AchievementInfo;
import test.io.api.litmos.response.CourseInfo;
import test.io.api.litmos.response.UserInfoWithCourses;

public interface LitmosCourseApi {
	/**
	 * 
	 * @param source
	 * @param since  {YYYY-MM-DD HH:MM:SS}
	 * @param userid
	 * @return
	 */
	@GET("achievements")
	Call<List<AchievementInfo>> getUserAchievements(@Query("source") String source, @Query("since") String since,
			@Query("userid") String userid);

	// ----USER ASSIGNMENTS & RESULTS VIA API----
	/**
	 * 
	 * @param userId
	 * @param source
	 * @return
	 */
	@GET("users/{userId}/courses")
	Call<List<CourseInfo>> getUserCourses(@Path("userId") String userId, @Query("source") String source);

	/**
	 * 
	 * @param userWrapper XML body
	 * @param source
	 * @return
	 */
	@POST("users/courses")
	Call<List<UserInfoWithCourses>> getUserCoursesByIds(@Body RequestBody userWrapper, @Query("source") String source);

	/**
	 * 
	 * @param userId
	 * @param courseId
	 * @param source
	 * @return
	 */
	@GET("users/{userId}/courses/{courseId}")
	Call<CourseInfo> getUserCourseSpecific(@Path("userId") String userId, @Path("courseId") String courseId,
			@Query("source") String source);

	/**
	 * 
	 * @param userId
	 * @param courseId
	 * @param source
	 * @return
	 */
	@PUT("users/{userId}/courses/{courseId}")
	Call<String> resetUserCourseSpecific(@Path("userId") String userId, @Path("courseId") String courseId,
			@Query("source") String source);

	/**
	 * 
	 * @param userId
	 * @param courseId
	 * @param source
	 * @return
	 */
	@DELETE("users/{userId}/courses/{courseId}")
	Call<String> removeUserCourseSpecific(@Path("userId") String userId, @Path("courseId") String courseId,
			@Query("source") String source);

	// ----USER COURSE ASSIGNMENTS----
	/**
	 * 
	 * @param userId
	 * @param ids
	 * @param source
	 * @return
	 */
	@POST("users/{userId}/courses")
	Call<String> assignCoursesToUser(@Path("userId") String userId, @Body List<IdWrapper> ids,
			@Query("source") String source);

	/**
	 * 
	 * @param userId
	 * @param courseWrapper XML body
	 * @param source
	 * @return
	 */
	@POST("users/{userId}/learningpaths")
	Call<String> assignLearningPathsToUser(@Path("userId") String userId, @Body RequestBody learningPathsWrapper,
			@Query("source") String source);

	// ----TEAMS COURSE ASSIGNMENTS----
	/**
	 * 
	 * @param teamsId
	 * @param source
	 * @return
	 */
	@GET("teams/{teamsId}/courses")
	Call<List<CourseInfo>> getCourseByTeamsId(@Path("teamsId") String teamsId, @Query("source") String source);

	/**
	 * 
	 * @param teamsId
	 * @param source
	 * @return
	 */
	@GET("teams/{teamsId}/learningpaths")
	Call<String> getLearningPathsByTeamsId(@Path("teamsId") String teamsId, @Query("source") String source);

	/**
	 * 
	 * @param teamsId
	 * @param requestBody
	 * @param source
	 * @return
	 */
	@POST("teams/{teamsId}/courses")
	Call<String> assignCoursesToTeams(@Path("teamsId") String teamsId, @Body RequestBody requestBody,
			@Query("source") String source);
}

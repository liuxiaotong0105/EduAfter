package cc.mrbird.system.dao;

import java.util.List;

import cc.mrbird.common.config.MyMapper;
import cc.mrbird.system.domain.Movie;
import cc.mrbird.system.domain.User;
import cc.mrbird.system.domain.UserWithRole;

public interface UserMapper extends MyMapper<User> {

	List<User> findUserWithDept(User user);
	
	List<UserWithRole> findUserWithRole(Long userId);
	
	User findUserProfile(User user);

    List<Movie> findMovie(Movie movie);

	void updateStatus(Movie movie);

	void updateStatusNo(Movie movie);
}